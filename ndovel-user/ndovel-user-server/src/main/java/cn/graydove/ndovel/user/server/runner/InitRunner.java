package cn.graydove.ndovel.user.server.runner;

import cn.graydove.ndovel.user.api.crypto.PasswordEncoder;
import cn.graydove.ndovel.user.api.enums.RoleEnum;
import cn.graydove.ndovel.user.api.config.UserProperties;
import cn.graydove.ndovel.user.server.domain.entity.RoleDO;
import cn.graydove.ndovel.user.server.domain.entity.UserDO;
import cn.graydove.ndovel.user.server.repostitory.RoleRepository;
import cn.graydove.ndovel.user.server.repostitory.UserRepository;
import lombok.AllArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author graydove
 */
@Component
@AllArgsConstructor
public class InitRunner implements ApplicationRunner {

    private final static String LOCK_NAME = "lock:user:init";

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private RedissonClient redissonClient;

    private UserProperties userProperties;

    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RLock lock = redissonClient.getLock(LOCK_NAME);
        if (lock.tryLock()) {
            try {
                Set<String> roleNames = Arrays.stream(RoleEnum.values()).map(RoleEnum::name).collect(Collectors.toSet());
                Set<RoleDO> roleDOSet = roleRepository.findAllByNameIn(roleNames);
                Set<String> names = roleDOSet.stream().map(RoleDO::getName).collect(Collectors.toSet());
                for (String roleName : roleNames) {
                    if (names.contains(roleName)) {
                        continue;
                    }
                    RoleDO roleDO = new RoleDO();
                    roleDO.setName(roleName);
                    RoleDO role = roleRepository.save(roleDO);
                    roleDOSet.add(role);
                }

                Optional<UserDO> user = userRepository.findByUsername(userProperties.getAdminUsername());
                if (!user.isPresent()) {
                    UserDO userDO = new UserDO();
                    userDO.setUsername(userProperties.getAdminUsername());
                    userDO.setPassword(passwordEncoder.encode(userProperties.getAdminPassword()));
                    userDO.setRoles(roleDOSet);
                    userRepository.save(userDO);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}

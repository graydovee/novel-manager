package cn.graydove.ndovel.user.server.service.impl;

import cn.graydove.ndovel.user.api.crypto.PasswordEncoder;
import cn.graydove.ndovel.user.api.enums.RoleEnum;
import cn.graydove.ndovel.user.api.model.dto.UserDTO;
import cn.graydove.ndovel.user.api.model.request.AddRoleRequest;
import cn.graydove.ndovel.user.api.model.request.UpdateUserRequest;
import cn.graydove.ndovel.user.api.model.vo.UserVO;
import cn.graydove.ndovel.user.server.domain.entity.RoleDO;
import cn.graydove.ndovel.user.server.domain.entity.UserDO;
import cn.graydove.ndovel.user.server.repostitory.RoleRepository;
import cn.graydove.ndovel.user.server.repostitory.UserRepository;
import cn.graydove.ndovel.user.server.service.UserService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author graydove
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final static CopyOptions COPY_OPTIONS = new CopyOptions(null, true, "roles");

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserVO findUserById(Long userId) {
        return userRepository.findById(userId).map(this::toUserVO).orElse(null);
    }

    @Override
    public UserVO findUserByUserName(String username) {
        return userRepository.findByUsername(username).map(this::toUserVO).orElse(null);
    }

    @Override
    public UserVO createUser(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        userDO.setNickname(userDTO.getNickname());
        userDO.setUsername(userDTO.getUsername());
        userDO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDO.setRoles(roleRepository.findAllByNameIn(Collections.singleton(RoleEnum.ROLE_READER.name())));
        userRepository.save(userDO);
        return toUserVO(userDO);
    }

    @Override
    public UserVO updateUser(UpdateUserRequest request) {
        if (null != request.getId()) {
            return userRepository.findById(request.getId()).map(userDO -> {
                if (CollectionUtil.isNotEmpty(request.getRoles())) {
                    userDO.setRoles(roleRepository.findAllByNameIn(request.getRoles()));
                }
                Optional.ofNullable(request.getUserName()).ifPresent(userDO::setUsername);
                Optional.ofNullable(request.getPassword()).ifPresent(userDO::setPassword);
                userRepository.save(userDO);
                return toUserVO(userDO);
            }).orElse(null);
        }
        return null;
    }

    @Override
    public UserVO addRole(AddRoleRequest addRoleRequest) {
        if (CollectionUtil.isEmpty(addRoleRequest.getRoles())) {
            return null;
        }
        return userRepository.findById(addRoleRequest.getUserId()).map(userDO -> {
            Set<RoleDO> roles = roleRepository.findAllByNameIn(addRoleRequest.getRoles());
            if (CollectionUtil.isEmpty(roles)) {
                return null;
            }
            userDO.getRoles().addAll(roles);
            userRepository.save(userDO);
            return toUserVO(userDO);
        }).orElse(null);
    }

    private UserVO toUserVO(@NotNull UserDO userDO) {
        UserVO userVO = BeanUtil.toBean(userDO, UserVO.class, COPY_OPTIONS);
        userVO.setRoles(userDO.getRoles().stream().map(RoleDO::getName).collect(Collectors.toSet()));
        return userVO;
    }

}

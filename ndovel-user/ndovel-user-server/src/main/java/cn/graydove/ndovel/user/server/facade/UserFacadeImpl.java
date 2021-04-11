package cn.graydove.ndovel.user.server.facade;

import cn.graydove.ndovel.user.api.domain.request.AddRoleRequest;
import cn.graydove.ndovel.user.api.domain.request.UpdateUserRequest;
import cn.graydove.ndovel.user.api.domain.request.UserRequest;
import cn.graydove.ndovel.user.api.domain.vo.UserVO;
import cn.graydove.ndovel.user.api.facade.UserFacade;
import cn.graydove.ndovel.user.server.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author graydove
 */
@DubboService
@AllArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private UserService userService;

    @Override
    public UserVO loadUser(Long userId) {
        return userService.findUserById(userId);
    }

    @Override
    public UserVO register(UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @Override
    public UserVO updateUser(UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }

    @Override
    public UserVO addRoles(AddRoleRequest addRoleRequest) {
        return userService.addRole(addRoleRequest);
    }

}

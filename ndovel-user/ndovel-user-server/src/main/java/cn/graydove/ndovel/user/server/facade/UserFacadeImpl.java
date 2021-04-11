package cn.graydove.ndovel.user.server.facade;

import cn.graydove.ndovel.user.api.model.dto.UserDTO;
import cn.graydove.ndovel.user.api.model.request.AddRoleRequest;
import cn.graydove.ndovel.user.api.model.request.UpdateUserRequest;
import cn.graydove.ndovel.user.api.model.vo.UserVO;
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
    public UserVO register(UserDTO userDTO) {
        return userService.createUser(userDTO);
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

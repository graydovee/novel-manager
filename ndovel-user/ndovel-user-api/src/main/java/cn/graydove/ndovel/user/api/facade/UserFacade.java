package cn.graydove.ndovel.user.api.facade;

import cn.graydove.ndovel.user.api.model.dto.UserDTO;
import cn.graydove.ndovel.user.api.model.request.AddRoleRequest;
import cn.graydove.ndovel.user.api.model.request.UpdateUserRequest;
import cn.graydove.ndovel.user.api.model.vo.UserVO;

/**
 * @author graydove
 */
public interface UserFacade {

    UserVO loadUser(Long userId);

    UserVO register(UserDTO userDTO);

    UserVO updateUser(UpdateUserRequest updateUserRequest);

    UserVO addRoles(AddRoleRequest addRoleRequest);
}

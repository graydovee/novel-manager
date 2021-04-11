package cn.graydove.ndovel.user.api.facade;

import cn.graydove.ndovel.user.api.domain.request.AddRoleRequest;
import cn.graydove.ndovel.user.api.domain.request.UpdateUserRequest;
import cn.graydove.ndovel.user.api.domain.request.UserRequest;
import cn.graydove.ndovel.user.api.domain.vo.UserVO;

/**
 * @author graydove
 */
public interface UserFacade {

    UserVO loadUser(Long userId);

    UserVO register(UserRequest userRequest);

    UserVO updateUser(UpdateUserRequest updateUserRequest);

    UserVO addRoles(AddRoleRequest addRoleRequest);
}

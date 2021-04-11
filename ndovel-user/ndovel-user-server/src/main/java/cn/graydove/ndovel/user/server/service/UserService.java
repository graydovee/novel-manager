package cn.graydove.ndovel.user.server.service;

import cn.graydove.ndovel.user.api.domain.request.AddRoleRequest;
import cn.graydove.ndovel.user.api.domain.request.UpdateUserRequest;
import cn.graydove.ndovel.user.api.domain.request.UserRequest;
import cn.graydove.ndovel.user.api.domain.vo.UserVO;

/**
 * @author graydove
 */
public interface UserService {

    UserVO findUserById(Long userId);

    UserVO createUser(UserRequest userRequest);

    UserVO updateUser(UpdateUserRequest request);

    UserVO addRole(AddRoleRequest addRoleRequest);
}

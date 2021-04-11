package cn.graydove.ndovel.user.server.service;

import cn.graydove.ndovel.user.api.model.dto.UserDTO;
import cn.graydove.ndovel.user.api.model.request.AddRoleRequest;
import cn.graydove.ndovel.user.api.model.request.UpdateUserRequest;
import cn.graydove.ndovel.user.api.model.vo.UserVO;

/**
 * @author graydove
 */
public interface UserService {

    UserVO findUserById(Long userId);

    UserVO findUserByUserName(String username);

    UserVO createUser(UserDTO userDTO);

    UserVO updateUser(UpdateUserRequest request);

    UserVO addRole(AddRoleRequest addRoleRequest);
}

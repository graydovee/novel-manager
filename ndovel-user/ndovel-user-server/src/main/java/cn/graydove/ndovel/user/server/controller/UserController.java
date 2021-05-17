package cn.graydove.ndovel.user.server.controller;

import cn.graydove.ndovel.client.UserContext;
import cn.graydove.ndovel.common.exception.TaskException;
import cn.graydove.ndovel.common.response.Response;
import cn.graydove.ndovel.common.response.ResponseStatus;
import cn.graydove.ndovel.user.api.enums.RoleEnum;
import cn.graydove.ndovel.user.api.model.dto.LoginDTO;
import cn.graydove.ndovel.user.api.model.dto.RefreshTokenDTO;
import cn.graydove.ndovel.user.api.model.dto.RoleDTO;
import cn.graydove.ndovel.user.api.model.dto.UserDTO;
import cn.graydove.ndovel.user.api.model.request.AddRoleRequest;
import cn.graydove.ndovel.user.api.model.vo.TokenVO;
import cn.graydove.ndovel.user.api.model.vo.UserVO;
import cn.graydove.ndovel.user.server.service.TokenService;
import cn.graydove.ndovel.user.server.service.UserService;
import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author graydove
 */
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private TokenService tokenService;

    @GetMapping
    public Response<UserVO> user() {
        UserVO user = UserContext.getUser();
        if (null == user) {
            return Response.fail(ResponseStatus.UNAUTHORIZED);
        }
        user.setPassword(null);
        return Response.success(user);
    }

    @PostMapping("/login")
    public TokenVO login(@Valid @RequestBody LoginDTO loginDTO) {
        return tokenService.login(loginDTO);
    }

    @PostMapping("/login_forever")
    public String loginForever(@Valid @RequestBody LoginDTO loginDTO) {
        return tokenService.loginForever(loginDTO);
    }

    @PostMapping("/refresh")
    public TokenVO refresh(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
        return tokenService.refreshToken(refreshTokenDTO);
    }

    @PostMapping("/register")
    public UserVO register(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PostMapping("/role")
    public Response addRole(@Valid @RequestBody RoleDTO roleDTO) {
        UserVO user = UserContext.getUser();
        Boolean b = Optional.ofNullable(user)
                .map(UserVO::getRoles)
                .map(roles -> roles.contains(RoleEnum.ROLE_ADMIN.name()))
                .orElse(false);
        if (!b) {
            return Response.fail("forbidden");
        }
        UserVO userVO = userService.addRole(BeanUtil.toBean(roleDTO, AddRoleRequest.class));
        return userVO == null ? Response.fail() : Response.ok();
    }
}

package cn.graydove.ndovel.user.server.controller;

import cn.graydove.ndovel.client.UserContext;
import cn.graydove.ndovel.common.exception.TaskException;
import cn.graydove.ndovel.common.response.Response;
import cn.graydove.ndovel.common.response.ResponseStatus;
import cn.graydove.ndovel.user.api.constant.AuthConstant;
import cn.graydove.ndovel.user.api.crypto.PasswordEncoder;
import cn.graydove.ndovel.user.api.enums.RoleEnum;
import cn.graydove.ndovel.user.api.model.dto.LoginDTO;
import cn.graydove.ndovel.user.api.model.dto.RefreshTokenDTO;
import cn.graydove.ndovel.user.api.model.dto.UserDTO;
import cn.graydove.ndovel.user.api.model.vo.TokenVO;
import cn.graydove.ndovel.user.api.model.vo.UserVO;
import cn.graydove.ndovel.user.api.token.TokenManager;
import cn.graydove.ndovel.user.api.token.TokenSubject;
import cn.graydove.ndovel.user.api.token.TokenType;
import cn.graydove.ndovel.user.api.util.TokenUtil;
import cn.graydove.ndovel.user.server.service.TokenService;
import cn.graydove.ndovel.user.server.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @PostMapping("/refresh")
    public TokenVO refresh(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
        return tokenService.refreshToken(refreshTokenDTO);
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody UserDTO userDTO) {
        userDTO.getRoles().add(RoleEnum.ROLE_READER.name());
        userService.createUser(userDTO);
    }
}

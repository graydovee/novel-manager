package cn.graydove.ndovel.user.server.controller;

import cn.graydove.ndovel.common.exception.TaskException;
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
import cn.graydove.ndovel.user.server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
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

    private PasswordEncoder passwordEncoder;

    private TokenManager tokenManager;

    @PostMapping("/login")
    public TokenVO login(@Valid @RequestBody LoginDTO loginDTO) {
        UserVO user = userService.findUserByUserName(loginDTO.getUsername());
        if (null == user) {
            throw new TaskException("用户不存在");
        }
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new TaskException("密码错误");
        }
        return newToken(user.getId());
    }

    @PostMapping("/refresh")
    public TokenVO refresh(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
        TokenSubject tokenSubject = tokenManager.parseToken(refreshTokenDTO.getRefreshToken());
        if (null == tokenSubject || tokenSubject.getTokenType() != TokenType.REFRESH_TOKEN) {
            throw new TaskException("无效令牌");
        }
        UserVO user = userService.findUserById(tokenSubject.getUserId());
        if (null == user) {
            throw new TaskException("用户不存在");
        }
        return newToken(user.getId());
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody UserDTO userDTO) {
        userDTO.getRoles().add(RoleEnum.ROLE_READER.name());
        userService.createUser(userDTO);
    }

    private TokenVO newToken(Long id) {
        TokenVO tokenVO = new TokenVO();
        tokenVO.setPrefix(AuthConstant.AUTH_PREFIX);
        tokenVO.setToken(tokenManager.createToken(new TokenSubject(id, TokenType.NORMAL_TOKEN)));
        tokenVO.setRefreshToken(tokenManager.createToken(new TokenSubject(id, TokenType.REFRESH_TOKEN), -1));
        return tokenVO;
    }
}

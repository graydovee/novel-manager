package cn.graydove.ndovel.user.server.service.impl;

import cn.graydove.ndovel.common.exception.TaskException;
import cn.graydove.ndovel.user.api.config.TokenProperties;
import cn.graydove.ndovel.user.api.constant.AuthConstant;
import cn.graydove.ndovel.user.api.crypto.PasswordEncoder;
import cn.graydove.ndovel.user.api.model.dto.LoginDTO;
import cn.graydove.ndovel.user.api.model.dto.RefreshTokenDTO;
import cn.graydove.ndovel.user.api.model.vo.TokenVO;
import cn.graydove.ndovel.user.api.model.vo.UserVO;
import cn.graydove.ndovel.user.api.token.TokenManager;
import cn.graydove.ndovel.user.api.token.TokenSubject;
import cn.graydove.ndovel.user.api.token.TokenType;
import cn.graydove.ndovel.user.api.util.TokenUtil;
import cn.graydove.ndovel.user.server.service.TokenService;
import cn.graydove.ndovel.user.server.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author graydove
 */
@Slf4j
@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    private UserService userService;

    private TokenManager tokenManager;

    private PasswordEncoder passwordEncoder;

    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper objectMapper;

    @Override
    public TokenVO login(LoginDTO loginDTO) {
        UserVO user = userService.findUserByUserName(loginDTO.getUsername());
        if (null == user) {
            throw new TaskException("用户不存在");
        }
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new TaskException("密码错误");
        }
        return createToken(user);
    }

    @Override
    public TokenVO refreshToken(RefreshTokenDTO refreshTokenDTO) {
        TokenSubject tokenSubject = tokenManager.parseToken(refreshTokenDTO.getRefreshToken());
        if (null == tokenSubject || tokenSubject.getTokenType() != TokenType.REFRESH_TOKEN) {
            throw new TaskException("无效令牌");
        }
        UserVO user = userService.findUserById(tokenSubject.getUserId());
        if (null == user) {
            throw new TaskException("用户不存在");
        }
        return createToken(user);
    }

    private TokenVO createToken(UserVO userVO) {
        String token = tokenManager.createToken(new TokenSubject(userVO.getId(), userVO.getRoles(), TokenType.NORMAL_TOKEN));
        try {
            stringRedisTemplate.opsForValue().set(
                    TokenUtil.toRedisKey(AuthConstant.AUTH_PREFIX + " " + token),
                    objectMapper.writeValueAsString(userVO),
                    tokenManager.getTtl(),
                    TimeUnit.MILLISECONDS
            );
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        TokenVO tokenVO = new TokenVO();
        tokenVO.setPrefix(AuthConstant.AUTH_PREFIX);
        tokenVO.setToken(token);
        tokenVO.setRefreshToken(tokenManager.createToken(new TokenSubject(userVO.getId(), userVO.getRoles(), TokenType.REFRESH_TOKEN), -1));
        return tokenVO;
    }
}

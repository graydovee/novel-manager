package cn.graydove.ndovel.user.server.service.impl;

import cn.graydove.ndovel.common.exception.TaskException;
import cn.graydove.ndovel.user.api.config.TokenProperties;
import cn.graydove.ndovel.user.api.constant.AuthConstant;
import cn.graydove.ndovel.user.api.crypto.PasswordEncoder;
import cn.graydove.ndovel.user.api.enums.RoleEnum;
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

import javax.validation.constraints.NotNull;
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
        UserVO userVO = assertLogin(loginDTO);
        return createToken(userVO);
    }

    @Override
    public String loginForever(LoginDTO loginDTO) {
        UserVO userVO = assertLogin(loginDTO);
        if (!userVO.getRoles().contains(RoleEnum.ROLE_ADMIN.name())) {
            throw new TaskException("forbidden");
        }
        String token = tokenManager.createToken(new TokenSubject(userVO.getId(), userVO.getRoles(), TokenType.NORMAL_TOKEN), -1);
        writeToCache(token, userVO, -1);
        return token;
    }

    @NotNull
    private UserVO assertLogin(LoginDTO loginDTO) {
        UserVO user = userService.findUserByUserName(loginDTO.getUsername());
        if (null == user) {
            throw new TaskException("用户不存在");
        }
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new TaskException("密码错误");
        }
        return user;
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

    @Override
    public Boolean writeToCache(String token, UserVO userVO) {
        return writeToCache(token, userVO, tokenManager.getTtl());
    }

    private Boolean writeToCache(String token, UserVO userVO, long ttl) {
        try {
            if (ttl > 0) {
                stringRedisTemplate.opsForValue().set(
                        TokenUtil.toRedisKey(AuthConstant.AUTH_PREFIX + " " + token),
                        objectMapper.writeValueAsString(userVO),
                        ttl,
                        TimeUnit.MILLISECONDS
                );
            } else {
                stringRedisTemplate.opsForValue().set(
                        TokenUtil.toRedisKey(AuthConstant.AUTH_PREFIX + " " + token),
                        objectMapper.writeValueAsString(userVO)
                );
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    private TokenVO createToken(UserVO userVO) {
        String token = tokenManager.createToken(new TokenSubject(userVO.getId(), userVO.getRoles(), TokenType.NORMAL_TOKEN));
        writeToCache(token, userVO);
        TokenVO tokenVO = new TokenVO();
        tokenVO.setPrefix(AuthConstant.AUTH_PREFIX);
        tokenVO.setToken(token);
        tokenVO.setRefreshToken(tokenManager.createToken(new TokenSubject(userVO.getId(), userVO.getRoles(), TokenType.REFRESH_TOKEN), -1));
        return tokenVO;
    }
}

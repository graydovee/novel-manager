package cn.graydove.ndovel.user.server.service;

import cn.graydove.ndovel.user.api.model.dto.LoginDTO;
import cn.graydove.ndovel.user.api.model.dto.RefreshTokenDTO;
import cn.graydove.ndovel.user.api.model.vo.TokenVO;
import cn.graydove.ndovel.user.api.model.vo.UserVO;

/**
 * @author graydove
 */
public interface TokenService {

    TokenVO login(LoginDTO loginDTO);

    String loginForever(LoginDTO loginDTO);

    TokenVO refreshToken(RefreshTokenDTO refreshTokenDTO);

    Boolean writeToCache(String token, UserVO userVO);
}

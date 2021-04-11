package cn.graydove.ndovel.user.api.util;

import cn.graydove.ndovel.user.api.constant.AuthConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author graydove
 */
public class TokenUtil {

    private final static String REDIS_TOKEN_PREFIX = "ndovel:token:user:";

    public static String toRedisKey(String key) {
        return REDIS_TOKEN_PREFIX + key;
    }


    public static String getTokenFromBearerToken(String bearerToken) {
        if (bearerToken == null) {
            return null;
        }
        String prefix = AuthConstant.AUTH_PREFIX;
        if (bearerToken.length() <= prefix.length() || !bearerToken.startsWith(prefix)) {
            return null;
        }
        return bearerToken.substring(prefix.length() + 1);
    }

    public static String toBearerToken(String token) {
        return AuthConstant.AUTH_PREFIX + " " + token;
    }


    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        if (request == null || cookieName == null) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }
        return null;
    }
}

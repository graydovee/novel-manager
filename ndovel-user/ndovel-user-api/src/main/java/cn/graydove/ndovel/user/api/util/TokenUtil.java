package cn.graydove.ndovel.user.api.util;

/**
 * @author graydove
 */
public class TokenUtil {

    private final static String REDIS_TOKEN_PREFIX = "ndovel:token:user:";

    public static String toRedisKey(String token) {
        return REDIS_TOKEN_PREFIX + token;
    }
}

package cn.graydove.ndovel.user.api.token;

public interface TokenManager {

    /**
     * 解密jwt
     */
    TokenSubject parseToken(String jwt);

    String createToken(TokenSubject subject);

    String createToken(TokenSubject subject, long ttl);

    long getTtl();
}

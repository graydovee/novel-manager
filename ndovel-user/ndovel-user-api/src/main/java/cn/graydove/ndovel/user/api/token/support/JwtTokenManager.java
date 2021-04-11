package cn.graydove.ndovel.user.api.token.support;

import cn.graydove.ndovel.common.Singleton;
import cn.graydove.ndovel.common.exception.TaskException;
import cn.graydove.ndovel.user.api.TokenException;
import cn.graydove.ndovel.user.api.config.TokenProperties;
import cn.graydove.ndovel.user.api.config.UserProperties;
import cn.graydove.ndovel.user.api.token.TokenManager;
import cn.graydove.ndovel.user.api.token.TokenSubject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author graydove
 */
@Component
@Slf4j
public class JwtTokenManager implements TokenManager {

    private ObjectMapper objectMapper;

    private TokenProperties tokenProperties;

    private Singleton<SecretKey> key = new Singleton<>();

    public JwtTokenManager(ObjectMapper objectMapper, UserProperties userProperties) {
        this.objectMapper = objectMapper;
        this.tokenProperties = userProperties.getToken();
    }

    private SecretKey getKey() {
        return key.get(() -> {
            byte[] encodedKey = Base64.getEncoder().encode(tokenProperties.getSecret().getBytes());
            return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        });
    }


    @Override
    public String createToken(TokenSubject subject, long ttl) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();

        SecretKey key = getKey();

        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(nowMillis))
                .setIssuer(tokenProperties.getIssuer())
                .setSubject(subjectToString(subject))
                .signWith(signatureAlgorithm, key);

        if (!tokenProperties.getClaims().isEmpty()) {
            builder.setClaims(tokenProperties.getClaims());
        }

        if (ttl >= 0) {
            long expMillis = nowMillis + tokenProperties.getTtl();
            builder.setExpiration(new Date(expMillis));
        }

        return builder.compact();
    }

    /**
     * 创建jwt
     */
    @Override
    public String createToken(TokenSubject subject) {
        return createToken(subject, tokenProperties.getTtl());
    }

    /**
     * 解密jwt
     */
    @Override
    public TokenSubject parseToken(String jwt) {
        SecretKey key = getKey();

        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            return null;
        }
        if (tokenProperties.getIssuer().equals(claims.getIssuer())) {
            return readTokenSubject(claims.getSubject());
        }
        return null;
    }

    private String subjectToString(TokenSubject subject) {
        try {
            return objectMapper.writeValueAsString(subject);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new TokenException("write token error");
        }
    }

    private TokenSubject readTokenSubject(String str) {
        try {
            return objectMapper.readValue(str, TokenSubject.class);
        } catch (JsonProcessingException e) {
            throw new TokenException("read token error");
        }
    }
}

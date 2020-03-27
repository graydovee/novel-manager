package com.ndovel.novel.security.core;

import com.ndovel.novel.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
@Getter
public class JwtManager {

    private JwtProperties jwtProperties;

    public JwtManager(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 由字符串生成加密key
     *
     */
    private SecretKey generalKey() {
        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(jwtProperties.getSecret());
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }


    public String createJWT(String subject, long ttl) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();

        SecretKey key = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(nowMillis))
                .setIssuer(jwtProperties.getIssuer())
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);

        if (!jwtProperties.getClaims().isEmpty()) {
            builder.setClaims(jwtProperties.getClaims());
        }

        if (ttl >= 0) {
            long expMillis = nowMillis + jwtProperties.getTtl();
            builder.setExpiration(new Date(expMillis));
        }

        return builder.compact();
    }

    /**
     * 创建jwt
     */
    public String createJWT(String subject) {

        return createJWT(subject, jwtProperties.getTtl());
    }

    /**
     * 解密jwt
     */
    public Claims parseJWT(String jwt) {
        SecretKey key = generalKey();  //签名秘钥，和生成的签名的秘钥一模一样

        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
    }

}
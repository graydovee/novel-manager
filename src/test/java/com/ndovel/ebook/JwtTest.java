package com.ndovel.ebook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndovel.ebook.model.entity.Authority;
import com.ndovel.ebook.model.entity.User;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

public class JwtTest extends ApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtCreator jwtCreator;

    @Test
    public void testjwt(){
        User user = new User();
        user.setUsername("123");
        user.setId(1);
        user.setPassword("123123");

        HashSet<Authority> authorities = new HashSet<>();
        Authority authority = new Authority();
        authority.setName("ROOT");
        authorities.add(authority);
        user.setAuthorities(authorities);

        String subject = null;
        try {
            subject = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        String jwt = jwtCreator.createJWT(subject);
        System.out.println("JWT：" + jwt);
        System.out.println(jwt.length());

        System.out.println("解密");

        Claims c = jwtCreator.parseJWT(jwt);
        System.out.println(c.getId());
        System.out.println(c.getIssuedAt());
        System.out.println(c.getSubject());
        System.out.println(c.getIssuer());
        System.out.println(c.get("id", String.class));
        System.out.println(c.get("user_name", String.class));
        System.out.println(c.get("nick_name", String.class));

    }

}

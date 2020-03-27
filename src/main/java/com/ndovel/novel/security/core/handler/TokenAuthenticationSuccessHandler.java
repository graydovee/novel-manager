package com.ndovel.novel.security.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndovel.novel.model.dto.UserDTO;
import com.ndovel.novel.model.entity.User;
import com.ndovel.novel.model.vo.JwtVO;
import com.ndovel.novel.security.core.JwtManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class TokenAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper;

    private JwtManager jwtManager;


    public TokenAuthenticationSuccessHandler(ObjectMapper objectMapper, JwtManager jwtManager) {
        this.objectMapper = objectMapper;
        this.jwtManager = jwtManager;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        UserDTO user = new UserDTO().init((User)authentication.getPrincipal());
        user.setPassword(null);
        String token = jwtManager.createJWT(objectMapper.writeValueAsString(user));

        JwtVO jwt = new JwtVO(token, jwtManager.getJwtProperties().getPrefix());

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(jwt));

    }

}

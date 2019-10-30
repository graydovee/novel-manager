package com.ndovel.ebook.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndovel.ebook.model.vo.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component("tokenAuthenticationFailureHandler")
public class TokenAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper;

    public TokenAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {

        HttpStatus status = HttpStatus.FORBIDDEN;
        response.setStatus(status.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new Response(status, exception.getMessage())));
    }
}

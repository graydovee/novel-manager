package com.ndovel.novel.security.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndovel.novel.exception.UnauthorizedException;
import com.ndovel.novel.model.vo.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAccessDeniedHandler implements AccessDeniedHandler {
    private ObjectMapper objectMapper;

    public TokenAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        HttpStatus status = e instanceof UnauthorizedException ? HttpStatus.UNAUTHORIZED : HttpStatus.FORBIDDEN;
        httpServletResponse.setStatus(status.value());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(Response.pack(status, e.getMessage())));
    }
}

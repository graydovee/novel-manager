package com.ndovel.ebook.security.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndovel.ebook.config.JwtProperties;
import com.ndovel.ebook.exception.UnauthorizedException;
import com.ndovel.ebook.model.dto.UserDTO;
import com.ndovel.ebook.security.core.handler.TokenAccessDeniedHandler;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtManager jwtManager;

    private ObjectMapper objectMapper;

    private JwtProperties jwtProperties;

    private TokenAccessDeniedHandler tokenAccessDeniedHandler;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JwtManager jwtManager,
                                  ObjectMapper objectMapper,
                                  TokenAccessDeniedHandler tokenAccessDeniedHandler) {
        super(authenticationManager);
        this.jwtManager = jwtManager;
        this.objectMapper = objectMapper;
        this.tokenAccessDeniedHandler = tokenAccessDeniedHandler;
        this.jwtProperties = jwtManager.getJwtProperties();
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (jwtProperties.getUnauthorizedUrl().contentEquals(request.getRequestURI())) {
            tokenAccessDeniedHandler.handle(request, response, new UnauthorizedException("请先登录"));
            return;
        }

        String tokenHeader = request.getHeader(jwtProperties.getHeader());

        // 检查请求头
        if (tokenHeader == null || !tokenHeader.startsWith(jwtProperties.getPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        // 如果请求头中有token，则进行解析，并且设置认证信息
        Authentication authentication;
        try {
            String token = tokenHeader.substring(jwtProperties.getPrefix().length() + 1);
            Claims claims = jwtManager.parseJWT(token);
            UserDTO user =  objectMapper.readValue(claims.getSubject(), UserDTO.class);
            authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }catch (Exception e){
            chain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}

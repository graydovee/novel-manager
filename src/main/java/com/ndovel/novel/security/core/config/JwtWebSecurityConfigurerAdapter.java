package com.ndovel.novel.security.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndovel.novel.security.core.JwtAuthorizationFilter;
import com.ndovel.novel.security.core.JwtManager;
import com.ndovel.novel.security.core.handler.TokenAccessDeniedHandler;
import com.ndovel.novel.security.core.handler.TokenAuthenticationFailureHandler;
import com.ndovel.novel.security.core.handler.TokenAuthenticationSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
public class JwtWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private TokenAuthenticationFailureHandler tokenAuthenticationFailureHandler;

    private TokenAuthenticationSuccessHandler tokenAuthenticationSuccessHandler;

    private TokenAccessDeniedHandler tokenAccessDeniedHandler;

    private JwtManager jwtManager;

    private ObjectMapper objectMapper;

    private HttpSecurityConfig httpSecurityConfig;

    public JwtWebSecurityConfigurerAdapter(TokenAuthenticationFailureHandler tokenAuthenticationFailureHandler,
                                           TokenAuthenticationSuccessHandler tokenAuthenticationSuccessHandler,
                                           TokenAccessDeniedHandler tokenAccessDeniedHandler,
                                           JwtManager jwtManager,
                                           ObjectMapper objectMapper,
                                           HttpSecurityConfig httpSecurityConfig) {
        this.tokenAuthenticationFailureHandler = tokenAuthenticationFailureHandler;
        this.tokenAuthenticationSuccessHandler = tokenAuthenticationSuccessHandler;
        this.tokenAccessDeniedHandler = tokenAccessDeniedHandler;
        this.jwtManager = jwtManager;
        this.objectMapper = objectMapper;
        this.httpSecurityConfig = httpSecurityConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(jwtManager.getJwtProperties().getUnauthorizedUrl())
                .loginProcessingUrl(jwtManager.getJwtProperties().getLoginProcessingUrl())
                .successHandler(tokenAuthenticationSuccessHandler)
                .failureHandler(tokenAuthenticationFailureHandler)
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtManager, objectMapper, tokenAccessDeniedHandler))
                .authorizeRequests()
                .antMatchers(jwtManager.getJwtProperties().getUnauthorizedUrl())
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(tokenAccessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable().cors();
        httpSecurityConfig.configureAuthorizeRequests(http.authorizeRequests());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

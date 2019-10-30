package com.ndovel.ebook.security.config;

import com.ndovel.ebook.security.TokenAuthenticationFailureHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private AuthenticationSuccessHandler tokenAuthenticationSuccessHandler;
    private TokenAuthenticationFailureHandler tokenAuthenticationFailureHandler;

    public ResourceServerConfig(AuthenticationSuccessHandler tokenAuthenticationSuccessHandler, TokenAuthenticationFailureHandler tokenAuthenticationFailureHandler) {
        this.tokenAuthenticationSuccessHandler = tokenAuthenticationSuccessHandler;
        this.tokenAuthenticationFailureHandler = tokenAuthenticationFailureHandler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

//        http.httpBasic()//httpBasic登录

        http.formLogin()
                .successHandler(tokenAuthenticationSuccessHandler)
                .failureHandler(tokenAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").authenticated()
                .antMatchers("/root/**").hasRole("ROOT")
                .anyRequest().permitAll()
                .and().cors()
                .and().csrf().disable();
    }

}

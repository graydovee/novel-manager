package com.ndovel.ebook.security.support;

import com.ndovel.ebook.security.core.config.HttpSecurityConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@Configuration("httpSecurityConfig")
@ConditionalOnMissingClass("httpSecurityConfig")
public class HttpSecurityConfigImpl implements HttpSecurityConfig {

    @Override
    public void configureAuthorizeRequests(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry request) {

        request.antMatchers("/admin/**").authenticated()
                .antMatchers("/root/**").hasRole("ROOT")
                .anyRequest().permitAll();
    }
}

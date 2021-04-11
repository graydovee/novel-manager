package cn.graydove.ndovel.user.api.config;

import cn.graydove.ndovel.user.api.crypto.PasswordEncoder;
import cn.graydove.ndovel.user.api.crypto.support.BCryptPasswordEncoder;
import cn.graydove.ndovel.user.api.token.TokenManager;
import cn.graydove.ndovel.user.api.token.support.JwtTokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author graydove
 */
@Configuration
@EnableConfigurationProperties(UserProperties.class)
public class UserConfiguration {

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(TokenManager.class)
    public TokenManager tokenManager(ObjectMapper objectMapper, UserProperties userProperties) {
        return new JwtTokenManager(objectMapper, userProperties);
    }
}

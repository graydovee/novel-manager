package cn.graydove.ndovel.gateway.config;

import cn.graydove.ndovel.gateway.auth.AuthorizationManager;
import cn.graydove.ndovel.user.api.enums.RoleEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author graydove
 */
@Configuration
public class AuthorizationConfiguration {

    @Bean
    public AuthorizationManager authorizationManager() {
        AuthorizationManager authorizationManager = new AuthorizationManager();
        authorizationManager.addMatch("/spider/**", RoleEnum.ROLE_ADMIN);
        authorizationManager.addMatch("/reader/**", RoleEnum.ROLE_READER);
        return authorizationManager;
    }
}

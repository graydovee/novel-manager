package cn.graydove.ndovel.user.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * @author graydove
 */
@Data
@Component
@ConfigurationProperties(prefix = "ndovel.user")
public class UserProperties {

    private String adminUsername = "ndovel";

    private String adminPassword = "ndovel";

    @NestedConfigurationProperty
    private TokenProperties token = new TokenProperties();
}

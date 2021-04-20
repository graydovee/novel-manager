package cn.graydove.ndovel.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * @author graydove
 */
@Data
@Component
@ConfigurationProperties(prefix = "novel")
public class NovelProperties {

    private boolean debug = true;

    private String version = "2.0.0";

    private String address = "http://localhost";

    @NestedConfigurationProperty
    private ControllerProperties controller = new ControllerProperties();

    @NestedConfigurationProperty
    private SwaggerProperties swagger = new SwaggerProperties();

    @NestedConfigurationProperty
    private OssProperties oss = new OssProperties();
}

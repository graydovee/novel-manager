package cn.graydove.ndovel.logger.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * @author graydove
 */
@Data
@Component
@ConfigurationProperties(prefix = "xxl.job")
public class XxlProperties {

    private String accessToken;

    @NestedConfigurationProperty
    private Admin admin = new Admin();

    @NestedConfigurationProperty
    private Executor executor = new Executor();

    @Data
    public static class Admin {
        private String addresses;
    }

    @Data
    public static class Executor {

        private String appName;

        private String address;

        private String ip;

        private int port;

        private String logPath;

        private Integer logRetentionDays;
    }
}

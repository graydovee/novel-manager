package cn.graydove.ndovel.spider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author graydove
 */
@Data
@Component
@ConfigurationProperties(prefix = "ndovel.spider")
public class SpiderProperties {

    private List<Invitor> invitor = new ArrayList<>();

    private String token;

    private String registerAddress;

    private String publishAddress;

    private String uid;

    private String publicKey;

    private String privateKey;

    @Data
    public static class Invitor {

        private String registerAddress;

        private String token;

        private String syncAddress;
    }
}

package com.ndovel.ebook.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    private String loginProcessingUrl = "/login";

    private String secret = "gray_dove";

    private String issuer = "gray_dove";

    private String header = "Authorization";

    private int ttl = 60 * 60 * 1000;

    private Map<String, Object> claims = Collections.emptyMap();

    private String prefix = "Bearer";

    private String unauthorizedUrl = "/unauthorized";

}

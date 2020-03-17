package com.ndovel.ebook.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    /**
     * app最新版本号
     */
    private String appVersion = "0.0.1";


    private String path = "./app";
}

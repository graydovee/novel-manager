package com.ndovel.ebook.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spider")
public class SpiderProperties {

    /**
     * 小说封面保存路径
     */
    private String coverPath = "./cover";
}

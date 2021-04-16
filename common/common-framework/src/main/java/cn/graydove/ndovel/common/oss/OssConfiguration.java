package cn.graydove.ndovel.common.oss;

import cn.graydove.ndovel.common.oss.OssTemplate;
import cn.graydove.ndovel.common.properties.NovelProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author graydove
 */
@Configuration
@ConditionalOnProperty(prefix = "novel.oss", name = "enable", havingValue = "true")
@EnableConfigurationProperties(NovelProperties.class)
public class OssConfiguration {

    @Bean
    public OssTemplate ossTemplate(NovelProperties novelProperties) {
        return new OssTemplate(novelProperties.getOss());
    }
}

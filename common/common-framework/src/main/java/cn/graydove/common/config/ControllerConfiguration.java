package cn.graydove.common.config;

import cn.graydove.common.advice.ControllerExceptionAdvice;
import cn.graydove.common.advice.ControllerResponseAdvice;
import cn.graydove.common.properties.NovelProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableConfigurationProperties(NovelProperties.class)
public class ControllerConfiguration extends WebMvcConfigurationSupport {

    private ObjectMapper objectMapper;

    private NovelProperties novelProperties;

    public ControllerConfiguration(ObjectMapper objectMapper, NovelProperties novelProperties) {
        this.objectMapper = objectMapper;
        this.novelProperties = novelProperties;
    }

    @Bean
    public ControllerExceptionAdvice controllerExceptionAdvice() {
        return new ControllerExceptionAdvice(objectMapper);
    }

    @Bean
    public ControllerResponseAdvice controllerResponseAdvice() {
        return new ControllerResponseAdvice(objectMapper, novelProperties);
    }

}

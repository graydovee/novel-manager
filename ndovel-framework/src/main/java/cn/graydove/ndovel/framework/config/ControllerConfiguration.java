package cn.graydove.ndovel.framework.config;

import cn.graydove.ndovel.framework.advice.ControllerExceptionAdvice;
import cn.graydove.ndovel.framework.advice.ControllerResponseAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ControllerConfiguration extends WebMvcConfigurationSupport {

    private ObjectMapper objectMapper;

    public ControllerConfiguration(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public ControllerExceptionAdvice controllerExceptionAdvice() {
        return new ControllerExceptionAdvice();
    }

    @Bean
    public ControllerResponseAdvice controllerResponseAdvice() {
        return new ControllerResponseAdvice(objectMapper);
    }

}

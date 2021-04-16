package cn.graydove.ndovel.common.config;

import cn.graydove.ndovel.common.ObjectMapperFactory;
import cn.graydove.ndovel.common.ObjectMapperFactoryImpl;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author graydove
 */
@Configuration
public class  ObjectMapperConfiguration {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    @Bean
    @ConditionalOnClass({ObjectMapper.class, JavaTimeModule.class})
    public ObjectMapperFactory objectMapperFactory() {
        return new ObjectMapperFactoryImpl(pattern);
    }

    @Bean
    public ObjectMapperBeanPostProcessor objectMapperBeanPostProcessor() {
        return new ObjectMapperBeanPostProcessor();
    }
}

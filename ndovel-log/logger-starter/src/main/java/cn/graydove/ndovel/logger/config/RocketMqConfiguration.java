package cn.graydove.ndovel.logger.config;

import cn.graydove.ndovel.logger.rocketmq.NovelProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author graydove
 */
@Configuration
public class RocketMqConfiguration {

    @Bean
    public NovelProducer novelProducer(RocketMQTemplate rocketMqTemplate) {
        return new NovelProducer(rocketMqTemplate);
    }
}

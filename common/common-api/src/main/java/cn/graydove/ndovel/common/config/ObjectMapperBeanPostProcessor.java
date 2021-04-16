package cn.graydove.ndovel.common.config;

import cn.graydove.ndovel.common.ObjectMapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author graydove
 */
@Slf4j
public class ObjectMapperBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ObjectMapperFactory objectMapperFactory;

    @Override
    public Object postProcessAfterInitialization(@NotNull Object bean, String beanName) throws BeansException {
        if (null == objectMapperFactory) {
            return null;
        }
        if (objectMapperFactory.isObjectMapper(bean)) {
            return objectMapperFactory.createObjectMapper();
        }
        return null;
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        try {
            this.objectMapperFactory = applicationContext.getBean(ObjectMapperFactory.class);
        } catch (Exception e) {
            log.warn("can not create objectMapper");
        }
    }
}

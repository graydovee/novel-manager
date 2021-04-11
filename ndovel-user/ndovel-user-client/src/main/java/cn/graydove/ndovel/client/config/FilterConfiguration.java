package cn.graydove.ndovel.client.config;

import cn.graydove.ndovel.client.filter.UserInfoFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author graydove
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<UserInfoFilter> userInfoFilter(StringRedisTemplate stringRedisTemplate, ObjectMapper objectMapper) {
        FilterRegistrationBean<UserInfoFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new UserInfoFilter(stringRedisTemplate, objectMapper));
        filterRegistrationBean.setOrder(Integer.MIN_VALUE);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("UserInfoFilter");
        return filterRegistrationBean;
    }
}

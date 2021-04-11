package cn.graydove.ndovel.common.advice;

import cn.graydove.ndovel.common.properties.ControllerProperties;
import cn.graydove.ndovel.common.properties.NovelProperties;
import cn.graydove.ndovel.common.response.Response;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

/**
 * 包裝
 * @author graydove
 */
@Slf4j
@RestControllerAdvice
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    private ObjectMapper objectMapper;

    private NovelProperties novelProperties;

    private ControllerProperties controllerProperties;

    public ControllerResponseAdvice(ObjectMapper objectMapper, NovelProperties novelProperties) {
        this.objectMapper = objectMapper;
        this.novelProperties = novelProperties;
        this.controllerProperties = novelProperties.getController();
    }

    @Override
    public boolean supports(@NotNull MethodParameter methodParameter, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        String controllerClassName = Optional.of(methodParameter)
                .map(MethodParameter::getMethod)
                .map(Method::getDeclaringClass)
                .map(Class::getName)
                .orElse(null);
        return checkControllerPackage(controllerClassName);
    }

    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter methodParameter, @NotNull MediaType mediaType, Class<? extends HttpMessageConverter<?>> convertType, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (novelProperties.isDebug()) {
            log.info("[{}] return: {}", methodParameter.getMethod(), body);
        }
        if (checkResponseBodyType(body)) {
            return body;
        }
        Response<Object> success = Response.success(body);
        if (convertType.isAssignableFrom(MappingJackson2HttpMessageConverter.class)) {
            return success;
        }
        if (convertType.isAssignableFrom(StringHttpMessageConverter.class)) {
            try {
                return objectMapper.writeValueAsString(success);
            } catch (JsonProcessingException e) {
                log.error("warp response body error:" + body.toString(), e);
            }
        }
        return body;
    }

    private boolean checkResponseBodyType(Object o) {
        String className = Optional.ofNullable(o)
                .map(Object::getClass)
                .map(Class::getName)
                .orElse(null);
        if (StrUtil.isBlank(className)) {
            return false;
        }
        List<String> ignoredResponseBodyClass = controllerProperties.getIgnoredResponseBodyClass();
        return CollectionUtil.isNotEmpty(ignoredResponseBodyClass) &&
                ignoredResponseBodyClass.contains(className);
    }

    private boolean checkControllerPackage(String packageName) {
        if (StrUtil.isBlank(packageName)) {
            return true;
        }
        for (String ignoredPackage: controllerProperties.getIgnoredControllerPackage()) {
            if (packageName.startsWith(ignoredPackage)) {
                return false;
            }
        }
        return true;
    }
}
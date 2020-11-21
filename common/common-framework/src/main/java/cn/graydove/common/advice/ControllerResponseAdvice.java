package cn.graydove.common.advice;

import cn.graydove.common.properties.ControllerProperties;
import cn.graydove.common.properties.NovelProperties;
import cn.graydove.common.response.Response;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Optional;

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
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return Optional.ofNullable(methodParameter.getMethod()).map(Method::getReturnType).map(clazz->!clazz.equals(void.class) && !clazz.equals(Void.class)).orElse(false);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> convertType, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (novelProperties.isDebug()) {
            log.info("[ {} ] return: {}", methodParameter.getMethod(), body);
        }
        if (isNeedIgnored(body, methodParameter)) {
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

    private boolean isNeedIgnored(Object o, MethodParameter methodParameter) {
        String controllerClassName = Optional.ofNullable(methodParameter)
                .map(MethodParameter::getMethod)
                .map(Method::getDeclaringClass)
                .map(Class::getName)
                .orElse(null);
        String responseBodyClassName = Optional.ofNullable(o)
                .map(Object::getClass)
                .map(Class::getName)
                .orElse(null);
        return checkControllerPackage(controllerClassName) || checkResponseBodyType(responseBodyClassName);
    }

    private boolean checkResponseBodyType(String className) {
        if (StrUtil.isBlank(className)) {
            return false;
        }
        for (String ignoredClassName: controllerProperties.getIgnoredResponseBodyClass()) {
            if (ignoredClassName.equals(className)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkControllerPackage(String packageName) {
        if (StrUtil.isBlank(packageName)) {
            return false;
        }
        for (String ignoredPackage: controllerProperties.getIgnoredControllerPackage()) {
            if (packageName.startsWith(ignoredPackage)) {
                return true;
            }
        }
        return false;
    }
}
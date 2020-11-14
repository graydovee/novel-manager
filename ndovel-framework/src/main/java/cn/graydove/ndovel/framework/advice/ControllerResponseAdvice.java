package cn.graydove.ndovel.framework.advice;

import cn.graydove.ndovel.api.response.Response;
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

    public ControllerResponseAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info(converterType.getSimpleName());
        return Optional.ofNullable(methodParameter.getMethod()).map(Method::getReturnType).map(clazz->!clazz.equals(void.class) && !clazz.equals(Void.class)).orElse(false);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> convertType, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body instanceof Response) {
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
}
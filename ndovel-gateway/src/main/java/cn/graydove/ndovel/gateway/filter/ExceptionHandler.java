package cn.graydove.ndovel.gateway.filter;

import cn.graydove.ndovel.common.response.Response;
import cn.graydove.ndovel.common.response.ResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author graydove
 */
@Slf4j
//@Component
@AllArgsConstructor
public class ExceptionHandler implements ErrorWebExceptionHandler {

    private ObjectMapper objectMapper;

    @NotNull
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
        response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        byte[] bytes = new byte[0];
        try {
            bytes = objectMapper.writeValueAsBytes(Response.fail(ResponseStatus.FAIL, ex.getMessage()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }
}

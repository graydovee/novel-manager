package cn.graydove.ndovel.gateway.filter;

import cn.graydove.ndovel.common.response.Response;
import cn.graydove.ndovel.common.response.ResponseStatus;
import cn.graydove.ndovel.gateway.auth.AuthorizationManager;
import cn.graydove.ndovel.user.api.constant.AuthConstant;
import cn.graydove.ndovel.user.api.enums.RoleEnum;
import cn.graydove.ndovel.user.api.model.vo.UserVO;
import cn.graydove.ndovel.user.api.token.TokenManager;
import cn.graydove.ndovel.user.api.token.TokenSubject;
import cn.graydove.ndovel.user.api.util.TokenUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author graydove
 */
@Slf4j
@Component
@AllArgsConstructor
public class TokenFilter implements GlobalFilter, Ordered {

    private ObjectMapper objectMapper;

    private AuthorizationManager authorizationManager;

    private TokenManager tokenManager;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        RoleEnum needRole = authorizationManager.getNeedRole(request.getPath().value());
        if (needRole != null) {
            //需要鉴权
            List<String> list = request.getHeaders().get(AuthConstant.AUTHORIZATION);
            if (CollectionUtil.isEmpty(list) || list.size() != 1) {
                return write(exchange, HttpStatus.UNAUTHORIZED, ResponseStatus.UNAUTHORIZED, "未登录");
            }
            String bearerToken = list.get(0);
            String token = TokenUtil.getTokenFromBearerToken(bearerToken);
            TokenSubject tokenSubject = tokenManager.parseToken(token);
            if (null == tokenSubject) {
                return write(exchange, HttpStatus.FORBIDDEN, ResponseStatus.FORBIDDEN, "无效令牌");
            }

            if (!tokenSubject.getRoles().contains(needRole.name())) {
                return write(exchange, HttpStatus.FORBIDDEN, ResponseStatus.FORBIDDEN, "无权限");
            }
        }
        return chain.filter(exchange);
    }

    private Mono<Void> write(ServerWebExchange exchange, HttpStatus httpStatus, ResponseStatus responseStatus, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        byte[] bytes = new byte[0];
        try {
            bytes = objectMapper.writeValueAsBytes(Response.fail(responseStatus, message));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }


    @Override
    public int getOrder() {
        return -1;
    }
}

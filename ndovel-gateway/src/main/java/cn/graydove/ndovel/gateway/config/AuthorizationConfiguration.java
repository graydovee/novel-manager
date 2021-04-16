package cn.graydove.ndovel.gateway.config;

import cn.graydove.ndovel.gateway.auth.AuthorizationManager;
import cn.graydove.ndovel.user.api.enums.RoleEnum;
import cn.hutool.core.util.StrUtil;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author graydove
 */
@Configuration
public class AuthorizationConfiguration {

    private final static Map<String, RoleEnum> SERVICE_MAP;

    static {
        SERVICE_MAP = new HashMap<>();
        SERVICE_MAP.put("spider", RoleEnum.ROLE_ADMIN);
        SERVICE_MAP.put("reader", RoleEnum.ROLE_READER);
        SERVICE_MAP.put("editor", RoleEnum.ROLE_EDITOR);
        SERVICE_MAP.put("author", RoleEnum.ROLE_AUTHOR);
        SERVICE_MAP.put("user", null);
    }

    /**
     * 等同于如下配置
     * routes:
     *      - id: spider
     *      uri: lb://ndovel-spider/
     *      filters:
     *      - StripPrefix=1
     *      predicates:
     *      - Path=/api/spider/**
     *      - id: reader
     *      uri: lb://ndovel-reader/
     *      filters:
     *      - StripPrefix=1
     *      predicates:
     *      - Path=/api/reader/**
     *      - id: user
     *      uri: lb://ndovel-user/
     *      filters:
     *      - StripPrefix=1
     *      predicates:
     *      - Path=/api/user/**
     *      - id: editor
     *      uri: lb://ndovel-editor/
     *      filters:
     *      - StripPrefix=1
     *      predicates:
     *      - Path=/api/editor/**
     *      - id: author
     *      uri: lb://ndovel-author/
     *      filters:
     *      - StripPrefix=1
     *      predicates:
     *      - Path=/api/author/**
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        for (Map.Entry<String, RoleEnum> entry : SERVICE_MAP.entrySet()) {
            String serviceName = entry.getKey();
            routes.route(serviceName, r -> r
                    .path(formatPath(serviceName))
                    .filters(f -> f.stripPrefix(2))
                    .uri(formatUri(serviceName))
            );
        }
        return routes.build();
    }

    @Bean
    public AuthorizationManager authorizationManager() {
        AuthorizationManager authorizationManager = new AuthorizationManager();
        for (Map.Entry<String, RoleEnum> entry : SERVICE_MAP.entrySet()) {
            authorizationManager.addMatch(formatPath(entry.getKey()), entry.getValue());
        }
        return authorizationManager;
    }

    private String formatPath(String serviceName) {
        return StrUtil.format("/api/{}/**", serviceName);
    }

    private String formatUri(String serviceName) {
        return StrUtil.format("lb://ndovel-{}/", serviceName);
    }
}

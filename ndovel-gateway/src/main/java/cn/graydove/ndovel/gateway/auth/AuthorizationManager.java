package cn.graydove.ndovel.gateway.auth;

import cn.graydove.ndovel.user.api.enums.RoleEnum;
import org.springframework.util.AntPathMatcher;

import java.util.HashMap;
import java.util.Map;

/**
 * @author graydove
 */
public class AuthorizationManager {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final Map<String, RoleEnum> map;

    public AuthorizationManager() {
        this.map = new HashMap<>();
    }

    public AuthorizationManager(Map<String, RoleEnum> map) {
        this.map = map;
    }

    public void addMatch(String pattern, RoleEnum roleEnum) {
        synchronized (map) {
            map.put(pattern, roleEnum);
        }
    }

    public RoleEnum getNeedRole(String uri) {
        for (Map.Entry<String, RoleEnum> entry : map.entrySet()) {
            if (antPathMatcher.match(entry.getKey(), uri)) {
                return entry.getValue();
            }
        }
        return null;
    }
}

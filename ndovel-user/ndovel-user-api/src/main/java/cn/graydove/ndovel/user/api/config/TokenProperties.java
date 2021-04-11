package cn.graydove.ndovel.user.api.config;

import lombok.Data;

import java.util.Collections;
import java.util.Map;

/**
 * @author graydove
 */
@Data
public class TokenProperties {

    private String secret = "gray_dove";

    private Map<String, Object> claims = Collections.emptyMap();

    private String issuer = "http://";

    private long ttl = 60 * 60 * 1000;
}

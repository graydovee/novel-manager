package cn.graydove.ndovel.user.api.model.vo;

import lombok.Data;

/**
 * @author graydove
 */
@Data
public class TokenVO {

    private String prefix;

    private String token;

    private String refreshToken;
}

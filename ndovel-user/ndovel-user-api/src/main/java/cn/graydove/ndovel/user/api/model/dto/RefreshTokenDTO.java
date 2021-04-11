package cn.graydove.ndovel.user.api.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class RefreshTokenDTO implements Serializable {

    private static final long serialVersionUID = 513454020556902770L;

    @NotBlank
    private String refreshToken;
}

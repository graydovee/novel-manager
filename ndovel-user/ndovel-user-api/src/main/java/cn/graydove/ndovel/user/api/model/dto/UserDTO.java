package cn.graydove.ndovel.user.api.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

/**
 * @author graydove
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 513454020556902770L;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    private Set<String> roles;
}

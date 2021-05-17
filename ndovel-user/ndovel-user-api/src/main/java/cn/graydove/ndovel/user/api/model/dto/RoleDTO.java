package cn.graydove.ndovel.user.api.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author graydove
 */
@Data
public class RoleDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Set<String> roles;
}

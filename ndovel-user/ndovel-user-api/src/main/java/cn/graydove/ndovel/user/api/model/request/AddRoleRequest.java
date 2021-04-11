package cn.graydove.ndovel.user.api.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author graydove
 */
@Data
public class AddRoleRequest implements Serializable {

    private static final long serialVersionUID = 8200537044028199844L;

    private Long userId;

    private Set<String> roles;
}

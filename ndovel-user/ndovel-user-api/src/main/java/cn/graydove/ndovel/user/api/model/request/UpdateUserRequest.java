package cn.graydove.ndovel.user.api.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author graydove
 */
@Data
public class UpdateUserRequest implements Serializable {

    private static final long serialVersionUID = -5521496690712747597L;

    private Long id;

    private String userName;

    private String password;

    private Set<String> roles;
}

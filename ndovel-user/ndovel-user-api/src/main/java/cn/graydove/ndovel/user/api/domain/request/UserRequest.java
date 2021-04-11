package cn.graydove.ndovel.user.api.domain.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author graydove
 */
@Data
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 513454020556902770L;

    private String userName;

    private String password;

    private Set<String> roles;
}

package cn.graydove.ndovel.user.api.domain.vo;

import cn.graydove.ndovel.common.model.BaseApi;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @author graydove
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserVO extends BaseApi {

    private static final long serialVersionUID = 4917279786045235921L;

    private String username;

    private String password;

    private Set<String> roles;
}

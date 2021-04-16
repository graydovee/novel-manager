package cn.graydove.ndovel.user.api.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @author graydove
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenSubject implements Serializable {

    private static final long serialVersionUID = -511139324706734291L;

    private Long userId;

    private Set<String> roles;

    private TokenType tokenType;

}

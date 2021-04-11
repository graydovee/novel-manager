package cn.graydove.ndovel.user.api.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenSubject implements Serializable {

    private Long userId;

    private TokenType tokenType;

}

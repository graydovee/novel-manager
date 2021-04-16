package cn.graydove.ndovel.author.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class SubmitDTO implements Serializable {

    private static final long serialVersionUID = -6402718760385672655L;

    @NotNull
    private Long id;
}

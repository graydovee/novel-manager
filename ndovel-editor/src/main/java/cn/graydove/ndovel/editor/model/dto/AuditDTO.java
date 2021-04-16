package cn.graydove.ndovel.editor.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class AuditDTO implements Serializable {

    private static final long serialVersionUID = -7483244275420473804L;

    @NotNull
    private Long id;

    @NotNull
    private Boolean result;
}

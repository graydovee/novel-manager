package cn.graydove.ndovel.author.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class UploadDTO implements Serializable {

    private static final long serialVersionUID = -1583890821937442598L;

    @NotNull
    private Long bookId;

    @NotBlank
    private String url;
}

package cn.graydove.ndovel.author.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class ChapterDTO implements Serializable {

    private static final long serialVersionUID = 8831361198112197709L;

    @NotNull
    private Long bookId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}

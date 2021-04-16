package cn.graydove.ndovel.editor.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class ChapterDTO implements Serializable {

    private static final long serialVersionUID = 1908069874910653140L;

    @NotNull
    private Long chapterId;
}

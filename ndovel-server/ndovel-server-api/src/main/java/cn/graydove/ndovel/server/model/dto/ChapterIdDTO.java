package cn.graydove.ndovel.server.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class ChapterIdDTO implements Serializable {

    private static final long serialVersionUID = 4083987346070030944L;

    @NotNull
    private Long chapterId;
}

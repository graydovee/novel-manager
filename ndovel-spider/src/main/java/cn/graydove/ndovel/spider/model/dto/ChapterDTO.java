package cn.graydove.ndovel.spider.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author graydove
 */
@Data
public class ChapterDTO {

    @NotNull
    private Long bookId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}

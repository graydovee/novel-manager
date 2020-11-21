package cn.graydove.ndovel.spider.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class NovelDTO {

    @NotBlank
    private String name;

    private String introduce;

    @NotBlank
    private String author;

    @NotBlank
    private String cover;

    @NotBlank
    private String firstChapterUrl;

    private List<String> type;
}

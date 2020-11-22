package cn.graydove.ndovel.spider.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class BookDTO {

    @NotBlank
    private String name;

    private String introduce;

    @NotBlank
    private String author;

    @NotBlank
    private String cover;

    @NotBlank
    private String firstChapterUrl;

    private Set<String> category;
}

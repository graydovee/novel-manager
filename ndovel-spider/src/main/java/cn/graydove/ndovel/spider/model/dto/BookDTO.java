package cn.graydove.ndovel.spider.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @author graydove
 */
@Data
public class BookDTO {

    @NotBlank
    private String name;

    private String introduce;

    @NotBlank
    private String author;

    @NotBlank
    private String cover;

    private Set<String> category;
}

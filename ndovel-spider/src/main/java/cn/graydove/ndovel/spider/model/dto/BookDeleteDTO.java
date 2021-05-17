package cn.graydove.ndovel.spider.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author graydove
 */
@Data
public class BookDeleteDTO {

    @NotNull
    Long id;
}

package cn.graydove.ndovel.author.model.dto;

import cn.graydove.ndovel.common.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author graydove
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageChapterDTO extends PageQuery {

    private static final long serialVersionUID = -265926550903056712L;

    @NotNull
    private Long bookId;
}

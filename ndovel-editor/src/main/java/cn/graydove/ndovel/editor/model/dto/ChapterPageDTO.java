package cn.graydove.ndovel.editor.model.dto;

import cn.graydove.ndovel.common.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author graydove
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChapterPageDTO extends PageQuery {

    private static final long serialVersionUID = -896595825623251503L;

    private Long bookId;
}

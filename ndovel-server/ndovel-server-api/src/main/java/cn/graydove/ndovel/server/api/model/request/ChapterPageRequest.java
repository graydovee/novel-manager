package cn.graydove.ndovel.server.api.model.request;

import cn.graydove.ndovel.common.query.PageQuery;
import cn.graydove.ndovel.server.api.enums.PublishStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

/**
 * @author graydove
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChapterPageRequest extends PageQuery {
    private static final long serialVersionUID = -5222677835449633764L;

    private Long bookId;

    private Collection<PublishStatus> statuses;

    private Boolean queryContent;
}

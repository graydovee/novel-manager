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
public class ChapterPageAllRequest extends PageQuery {

    private static final long serialVersionUID = -5003511403302934094L;

    private Collection<PublishStatus> statuses;

    private Boolean queryContent;
}

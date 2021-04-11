package cn.graydove.ndovel.server.model.dto;

import cn.graydove.common.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author graydove
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BookPageDTO extends PageQuery {

    private static final long serialVersionUID = -7385305628729895L;

}

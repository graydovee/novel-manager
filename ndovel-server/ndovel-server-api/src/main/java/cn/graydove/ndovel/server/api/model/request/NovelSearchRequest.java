package cn.graydove.ndovel.server.api.model.request;

import cn.graydove.ndovel.common.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @author graydove
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NovelSearchRequest extends PageQuery {

    private static final long serialVersionUID = 4554426362016916784L;

    private String name;

    private String author;

    private String introduce;

    private Set<String> type;
}

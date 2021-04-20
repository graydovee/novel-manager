package cn.graydove.ndovel.reader.model.dto;

import cn.graydove.ndovel.common.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @author graydove
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NovelSearchDTO extends PageQuery {

    private static final long serialVersionUID = 2845581690919965052L;

    private String name;

    private String author;

    private String introduce;

    private Set<String> type;
}

package cn.graydove.ndovel.server.api.model.vo;

import cn.graydove.ndovel.common.model.BaseApi;
import cn.graydove.ndovel.server.api.enums.PublishStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author graydove
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BookVO extends BaseApi {

    private static final long serialVersionUID = 6919515597453088200L;

    private String name;

    private String introduce;

    private AuthorVO author;

    private List<CategoryVO> type;

    private String cover;

    private Long firstChapterId;

    private Long lastChapterId;

    private PublishStatus status;
}

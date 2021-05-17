package cn.graydove.ndovel.server.api.model.vo;

import cn.graydove.ndovel.common.model.BaseApi;
import cn.graydove.ndovel.server.api.enums.PublishStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author graydove
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChapterVO extends BaseApi {

    private static final long serialVersionUID = 967613191811396722L;

    private Long bookId;

    private String title;

    private String content;

    private Long nextChapterId;

    private Long preChapterId;

    private PublishStatus status;
}
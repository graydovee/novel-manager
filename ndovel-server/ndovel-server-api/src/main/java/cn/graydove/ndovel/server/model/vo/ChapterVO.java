package cn.graydove.ndovel.server.model.vo;

import cn.graydove.common.model.BaseApi;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author graydove
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChapterVO extends BaseApi {

    private static final long serialVersionUID = 967613191811396722L;

    private String title;

    private String content;

    private Long nextChapterId;

    private Long preChapterId;
}

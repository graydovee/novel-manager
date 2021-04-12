package cn.graydove.ndovel.server.api.model.request;

import cn.graydove.ndovel.server.api.enums.PublishStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class ChapterRequest implements Serializable {

    private static final long serialVersionUID = -8162793803437973471L;

    private Long bookId;

    private String title;

    private String content;

    private PublishStatus status;
}

package cn.graydove.ndovel.server.api.model.request;

import cn.graydove.ndovel.server.api.enums.PublishStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class ChapterIdRequest implements Serializable {

    private static final long serialVersionUID = 4083987346070030944L;

    private PublishStatus publishStatus;

    private Long chapterId;
}

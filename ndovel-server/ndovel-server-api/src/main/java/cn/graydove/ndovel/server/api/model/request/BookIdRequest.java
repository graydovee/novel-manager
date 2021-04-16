package cn.graydove.ndovel.server.api.model.request;

import cn.graydove.ndovel.server.api.enums.PublishStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class BookIdRequest implements Serializable {

    private static final long serialVersionUID = 3900620867235623209L;

    private PublishStatus publishStatus;

    private Long bookId;
}

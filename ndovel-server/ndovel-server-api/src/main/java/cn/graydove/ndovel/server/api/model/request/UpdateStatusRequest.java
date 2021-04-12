package cn.graydove.ndovel.server.api.model.request;

import cn.graydove.ndovel.server.api.enums.PublishStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class UpdateStatusRequest implements Serializable {

    private static final long serialVersionUID = 973562116252614554L;

    private Long id;

    private PublishStatus status;

}

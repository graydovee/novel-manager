package cn.graydove.ndovel.server.api.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author graydove
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateBookRequest extends UpdateStatusRequest {

    private static final long serialVersionUID = 8703496503034187225L;

    private String name;

    private String introduce;
}

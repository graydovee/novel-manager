package cn.graydove.ndovel.server.api.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author graydove
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateChapterRequest extends UpdateStatusRequest {

    private static final long serialVersionUID = -766283215490478425L;

    private String content;

    private String title;
}

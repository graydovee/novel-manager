package cn.graydove.ndovel.server.api.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class BookDeleteRequest implements Serializable {

    private static final long serialVersionUID = -1003966714607537865L;

    Long bookId;
}

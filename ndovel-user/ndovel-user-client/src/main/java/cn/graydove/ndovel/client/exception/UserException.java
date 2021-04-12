package cn.graydove.ndovel.client.exception;

import cn.graydove.ndovel.common.exception.TaskException;
import cn.graydove.ndovel.common.response.ResponseStatus;

/**
 * @author graydove
 */
public class UserException extends TaskException {

    private static final long serialVersionUID = -5146890936342692549L;

    @Override
    public ResponseStatus getResponseStatus() {
        return ResponseStatus.USER_ERROR;
    }
}

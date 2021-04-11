package cn.graydove.ndovel.common.exception;

import cn.graydove.ndovel.common.response.ResponseStatus;

/**
 * @author graydove
 */
public class NotValidException extends AbstractNovelException {

    public NotValidException() {
    }

    public NotValidException(String message) {
        super(message);
    }

    public NotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidException(Throwable cause) {
        super(cause);
    }

    public NotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public ResponseStatus getResponseStatus() {
        return ResponseStatus.NOT_VALID;
    }
}

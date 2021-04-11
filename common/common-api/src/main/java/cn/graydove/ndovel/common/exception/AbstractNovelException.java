package cn.graydove.ndovel.common.exception;

import cn.graydove.ndovel.common.response.ResponseStatus;

/**
 * @author graydove
 */
public abstract class AbstractNovelException extends RuntimeException {

    public AbstractNovelException() {
    }

    public AbstractNovelException(String message) {
        super(message);
    }

    public AbstractNovelException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractNovelException(Throwable cause) {
        super(cause);
    }

    public AbstractNovelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public abstract ResponseStatus getResponseStatus();
}

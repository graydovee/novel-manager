package cn.graydove.ndovel.user.api;

import cn.graydove.ndovel.common.exception.TaskException;

/**
 * @author graydove
 */
public class TokenException extends TaskException {
    public TokenException() {
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(Throwable cause) {
        super(cause);
    }

    public TokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

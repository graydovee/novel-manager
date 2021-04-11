package cn.graydove.ndovel.common.exception;

/**
 * @author graydove
 */
public class TaskException extends RuntimeException {

    private static final long serialVersionUID = 4506559153241080085L;

    public TaskException() {
    }

    public TaskException(String message) {
        super(message);
    }

    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskException(Throwable cause) {
        super(cause);
    }

    public TaskException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

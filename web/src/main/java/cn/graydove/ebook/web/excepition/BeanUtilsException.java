package cn.graydove.ebook.web.excepition;

public class BeanUtilsException extends RuntimeException {
    public BeanUtilsException() {
    }

    public BeanUtilsException(String message) {
        super(message);
    }

    public BeanUtilsException(String message, Throwable cause) {
        super(message, cause);
    }
}

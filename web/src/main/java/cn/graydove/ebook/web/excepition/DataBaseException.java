package cn.graydove.ebook.web.excepition;

public class DataBaseException extends RuntimeException {
    public DataBaseException() {
    }

    public DataBaseException(String message) {
        super(message);
    }
}

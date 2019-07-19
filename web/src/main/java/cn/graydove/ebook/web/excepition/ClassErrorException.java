package cn.graydove.ebook.web.excepition;

public class ClassErrorException extends RuntimeException {
    public ClassErrorException() {
    }

    public ClassErrorException(String message) {
        super(message);
    }
}

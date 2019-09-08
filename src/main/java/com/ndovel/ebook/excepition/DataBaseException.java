package com.ndovel.ebook.excepition;

public class DataBaseException extends RuntimeException {
    public DataBaseException() {
    }

    public DataBaseException(String message) {
        super(message);
    }
}

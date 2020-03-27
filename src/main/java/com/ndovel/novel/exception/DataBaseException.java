package com.ndovel.novel.exception;

public class DataBaseException extends RuntimeException {
    public DataBaseException() {
    }

    public DataBaseException(String message) {
        super(message);
    }
}

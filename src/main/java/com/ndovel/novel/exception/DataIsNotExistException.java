package com.ndovel.novel.exception;

public class DataIsNotExistException extends RuntimeException {
    public DataIsNotExistException() {
    }

    public DataIsNotExistException(String message) {
        super(message);
    }
}

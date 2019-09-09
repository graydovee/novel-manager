package com.ndovel.ebook.exception;

public class DataIsNotExistException extends RuntimeException {
    public DataIsNotExistException() {
    }

    public DataIsNotExistException(String message) {
        super(message);
    }
}

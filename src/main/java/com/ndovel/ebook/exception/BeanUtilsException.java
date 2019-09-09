package com.ndovel.ebook.exception;

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

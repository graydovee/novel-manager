package com.ndovel.ebook.exception;

public class ClassErrorException extends RuntimeException {
    public ClassErrorException() {
    }

    public ClassErrorException(String message) {
        super(message);
    }
}

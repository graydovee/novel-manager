package com.ndovel.ebook.exception;

public class InvalidArgsException extends RuntimeException {
    public InvalidArgsException() {
    }

    public InvalidArgsException(String message) {
        super(message);
    }
}

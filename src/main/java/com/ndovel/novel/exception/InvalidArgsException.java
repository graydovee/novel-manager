package com.ndovel.novel.exception;

public class InvalidArgsException extends RuntimeException {
    public InvalidArgsException() {
    }

    public InvalidArgsException(String message) {
        super(message);
    }
}

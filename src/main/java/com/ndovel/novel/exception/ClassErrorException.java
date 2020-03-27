package com.ndovel.novel.exception;

public class ClassErrorException extends RuntimeException {
    public ClassErrorException() {
    }

    public ClassErrorException(String message) {
        super(message);
    }
}

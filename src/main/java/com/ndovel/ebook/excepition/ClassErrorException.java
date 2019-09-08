package com.ndovel.ebook.excepition;

public class ClassErrorException extends RuntimeException {
    public ClassErrorException() {
    }

    public ClassErrorException(String message) {
        super(message);
    }
}

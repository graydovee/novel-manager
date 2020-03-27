package com.ndovel.novel.exception;

public class UrlNullException extends RuntimeException{
    public UrlNullException() {
        super();
    }

    public UrlNullException(String message) {
        super(message);
    }
}

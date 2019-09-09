package com.ndovel.ebook.exception;

public class UrlNullException extends RuntimeException{
    public UrlNullException() {
        super();
    }

    public UrlNullException(String message) {
        super(message);
    }
}

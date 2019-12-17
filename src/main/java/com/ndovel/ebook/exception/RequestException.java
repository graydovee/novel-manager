package com.ndovel.ebook.exception;

public class RequestException extends Exception {
    public RequestException(String message) {
        super(message);
    }

    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }
    public RequestException(Throwable cause) {
        super(cause);
    }
}
package com.ndovel.novel.exception;

import org.springframework.security.access.AccessDeniedException;

public class UnauthorizedException extends AccessDeniedException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException(String msg, Throwable t) {
        super(msg, t);
    }
}

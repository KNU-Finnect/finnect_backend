package com.finnect.common.error;

public class DuplicateException extends RuntimeException {
    static int statusCode = 409;

    public DuplicateException(String message) {
        super(message);
    }
}

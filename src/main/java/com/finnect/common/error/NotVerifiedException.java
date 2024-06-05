package com.finnect.common.error;

public class NotVerifiedException extends RuntimeException {
    static int statusCode = 403;

    public NotVerifiedException(String message) {
        super(message);
    }
}

package com.finnect.user.application.port.out.exception;

public class EmailCodeNotFoundException extends RuntimeException {

    public EmailCodeNotFoundException(String email) {
        super("EmailCode(%s) is not found".formatted(email));
    }
}

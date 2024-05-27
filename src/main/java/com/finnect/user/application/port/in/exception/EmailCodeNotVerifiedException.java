package com.finnect.user.application.port.in.exception;

public class EmailCodeNotVerifiedException extends RuntimeException {

    public EmailCodeNotVerifiedException(String email) {
        super("EmailCode(%s) is not verified".formatted(email));
    }
}

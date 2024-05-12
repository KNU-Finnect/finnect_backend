package com.finnect.user.exception;

public class RefreshTokenNotFoundException extends RuntimeException {

    public RefreshTokenNotFoundException(String refreshToken) {
        super("RefreshToken(%s) is invalid".formatted(refreshToken));
    }
}

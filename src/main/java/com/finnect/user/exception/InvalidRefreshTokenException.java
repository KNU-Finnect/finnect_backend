package com.finnect.user.exception;

public class InvalidRefreshTokenException extends RuntimeException {

    public InvalidRefreshTokenException(String refreshToken, Throwable e) {
        super("RefreshToken(%s) is invalid".formatted(refreshToken), e);
    }
}

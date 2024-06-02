package com.finnect.user.application.port.in.exception;

public class UserDuplicateException extends RuntimeException {

    public UserDuplicateException(String username) {
        super("User(%s) is duplicate".formatted(username));
    }
}

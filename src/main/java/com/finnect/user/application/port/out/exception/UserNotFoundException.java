package com.finnect.user.application.port.out.exception;

import com.finnect.user.vo.UserId;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(UserId userId) {
        super("User(userId = %s) is not found".formatted(userId));
    }

    public UserNotFoundException(String username) {
        super("User(username = %s) is not found".formatted(username));
    }
}

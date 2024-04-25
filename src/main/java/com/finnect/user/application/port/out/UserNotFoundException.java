package com.finnect.user.application.port.out;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super("User(username = %s) is not found".formatted(username));
    }
}

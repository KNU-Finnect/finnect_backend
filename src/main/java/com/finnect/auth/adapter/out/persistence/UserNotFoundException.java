package com.finnect.auth.adapter.out.persistence;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super("User(username = %s) is not found".formatted(username));
    }
}

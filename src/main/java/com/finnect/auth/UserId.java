package com.finnect.auth;

public record UserId(Long value) {

    @Override
    public String toString() {
        return value.toString();
    }
}
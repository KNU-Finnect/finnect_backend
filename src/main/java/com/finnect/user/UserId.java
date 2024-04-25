package com.finnect.user;

public record UserId(Long value) {

    @Override
    public String toString() {
        return value.toString();
    }
}
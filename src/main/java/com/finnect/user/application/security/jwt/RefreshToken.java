package com.finnect.user.application.security.jwt;

public record RefreshToken(String value) {

    @Override
    public String toString() {
        return value;
    }
}

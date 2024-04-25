package com.finnect.user.adapter.out.jwt;

public record RefreshToken(String value) {

    @Override
    public String toString() {
        return value;
    }
}

package com.finnect.auth.adapter.out.jwt;

public record RefreshToken(String value) {

    @Override
    public String toString() {
        return value;
    }
}

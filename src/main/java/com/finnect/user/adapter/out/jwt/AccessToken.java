package com.finnect.user.adapter.out.jwt;

public record AccessToken(String value) {

    @Override
    public String toString() {
        return value;
    }

    public String toBearerString() {
        return "Bearer %s".formatted(value);
    }
}

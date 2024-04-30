package com.finnect.user.vo;

public record UserId(long value) {

    @Override
    public String toString() {
        return "%d".formatted(value);
    }
}
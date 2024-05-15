package com.finnect.user.vo;

public record UserId(long value) {

    @Override
    public String toString() {
        return "%d".formatted(value);
    }

    public static UserId parseOrNull(String value) {
        try {
            return new UserId(Long.parseLong(value));
        } catch (Exception e) {
            return null;
        }
    }
}
package com.finnect.user.vo;

public record UserId(Long value) {

    @Override
    public String toString() {
        return value.toString();
    }
}
package com.finnect.auth;

public record WorkspaceId(Long value) {

    @Override
    public String toString() {
        return value.toString();
    }
}

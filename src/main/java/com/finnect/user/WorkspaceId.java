package com.finnect.user;

public record WorkspaceId(Long value) {

    @Override
    public String toString() {
        return value.toString();
    }
}

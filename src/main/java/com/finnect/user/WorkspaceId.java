package com.finnect.user;

public record WorkspaceId(Long value) {

    @Override
    public String toString() {
        return value.toString();
    }

    public static WorkspaceId parseOrNull(String value) {
        try {
            return new WorkspaceId(Long.parseLong(value));
        } catch (Exception e) {
            return null;
        }
    }
}

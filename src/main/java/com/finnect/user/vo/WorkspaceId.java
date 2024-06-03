package com.finnect.user.vo;

public record WorkspaceId(Long value) {

    @Override
    public String toString() {
        return "%d".formatted(value);
    }

    public static WorkspaceId parseOrNull(Object value) {
        try {
            return WorkspaceId.parseOrNull(String.valueOf(value));
        } catch (Exception e) {
            return null;
        }
    }

    public static WorkspaceId parseOrNull(String value) {
        try {
            return new WorkspaceId(Long.valueOf(value));
        } catch (Exception e) {
            return null;
        }
    }
}

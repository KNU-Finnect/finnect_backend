package com.finnect.user.adapter.in.web.request;

public record Reissue2WorkspaceRequest(
        String refreshToken,
        Long workspaceId
) {
}

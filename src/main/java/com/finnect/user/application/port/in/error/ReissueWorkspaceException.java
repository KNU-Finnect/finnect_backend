package com.finnect.user.application.port.in.error;

import com.finnect.user.vo.UserId;
import com.finnect.user.vo.WorkspaceId;

public class ReissueWorkspaceException extends RuntimeException {

    public ReissueWorkspaceException(UserId userId, WorkspaceId workspaceId) {
        super("User(%d) cannot reissue to workspace(%d)".formatted(userId.value(), workspaceId.value()));
    }
}

package com.finnect.user.application.port.in.command;

import com.finnect.user.vo.WorkspaceId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class ReissueWorkspaceCommand {

    private final String refreshToken;

    private final WorkspaceId workspaceId;

    public ReissueWorkspaceCommand(
            @NonNull String refreshToken,
            @NonNull WorkspaceId workspaceId
    ) {
        this.refreshToken = refreshToken;
        this.workspaceId = workspaceId;
    }
}

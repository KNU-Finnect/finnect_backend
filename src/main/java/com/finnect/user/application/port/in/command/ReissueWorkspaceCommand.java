package com.finnect.user.application.port.in.command;

import com.finnect.common.vo.WorkspaceId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder @RequiredArgsConstructor
@Getter
public class ReissueWorkspaceCommand {

    @NonNull
    private final String refreshToken;

    @NonNull
    private final WorkspaceId workspaceId;
}

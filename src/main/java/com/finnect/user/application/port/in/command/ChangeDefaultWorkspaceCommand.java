package com.finnect.user.application.port.in.command;

import com.finnect.common.vo.UserId;
import com.finnect.common.vo.WorkspaceId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder @RequiredArgsConstructor
@Getter
public class ChangeDefaultWorkspaceCommand {

    @NonNull
    private final UserId userId;

    @NonNull
    private final WorkspaceId workspaceId;
}

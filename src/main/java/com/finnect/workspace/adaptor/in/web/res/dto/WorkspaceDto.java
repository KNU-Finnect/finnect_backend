package com.finnect.workspace.adaptor.in.web.res.dto;

import com.finnect.workspace.WorkspaceState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceDto {
    Long workspaceId;
    String workspaceName;

    public WorkspaceDto(WorkspaceState state) {
        this.workspaceId = state.getWorkspaceId();
        this.workspaceName = state.getWorkspaceName();
    }
}
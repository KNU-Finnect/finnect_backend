package com.finnect.workspace.adaptor.in.web.res.dto;

import com.finnect.workspace.WorkspaceState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceWithIdDto {
    Long workspaceId;
    String workspaceName;

    public static WorkspaceWithIdDto from(WorkspaceState state) {
        this.workspaceId = state.getWorkspaceId();
        this.workspaceName = state.getWorkspaceName();
        return this;
    }
}

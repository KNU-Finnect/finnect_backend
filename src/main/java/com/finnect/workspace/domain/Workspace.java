package com.finnect.workspace.domain;

import com.finnect.workspace.domain.state.WorkspaceState;
import lombok.Getter;

@Getter
public class Workspace implements WorkspaceState {
    private final Long workspaceId;

    private final String workspaceName;

    public Workspace(Long workspaceId, String workspaceName) {
        this.workspaceId = workspaceId;
        this.workspaceName = workspaceName;
    }

    public Workspace(String workspaceName) {
        this.workspaceId = null;
        this.workspaceName = workspaceName;
    }
}

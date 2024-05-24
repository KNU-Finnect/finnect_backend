package com.finnect.workspace.application.port.out;

import com.finnect.workspace.domain.state.WorkspaceState;

public interface CreateWorkspacePort {

    WorkspaceState createWorkspace(WorkspaceState workspaceState);
}

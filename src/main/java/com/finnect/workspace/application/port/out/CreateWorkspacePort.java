package com.finnect.workspace.application.port.out;

import com.finnect.workspace.WorkspaceState;

public interface CreateWorkspacePort {

    WorkspaceState createWorkspace(WorkspaceState workspaceState);
}

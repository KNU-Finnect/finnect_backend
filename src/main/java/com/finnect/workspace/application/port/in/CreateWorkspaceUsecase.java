package com.finnect.workspace.application.port.in;

import com.finnect.workspace.WorkspaceState;

public interface CreateWorkspaceUsecase {

    WorkspaceState createWorkspace(CreateWorkspaceCommand cmd);
}

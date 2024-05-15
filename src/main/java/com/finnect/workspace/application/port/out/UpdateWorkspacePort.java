package com.finnect.workspace.application.port.out;

import com.finnect.workspace.domain.state.WorkspaceState;

public interface UpdateWorkspacePort {

    WorkspaceState updateWorkspace(WorkspaceState state);
}

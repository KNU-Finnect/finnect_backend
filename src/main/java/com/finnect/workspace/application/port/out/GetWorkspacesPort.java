package com.finnect.workspace.application.port.out;

import com.finnect.workspace.domain.state.WorkspaceState;

import java.util.List;

public interface GetWorkspacesPort {

    List<WorkspaceState> getWorkspacesByUserId(Long userId);
}

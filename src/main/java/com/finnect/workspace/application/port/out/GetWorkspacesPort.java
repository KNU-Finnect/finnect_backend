package com.finnect.workspace.application.port.out;

import com.finnect.workspace.domain.state.WorkspaceState;

import java.util.List;

public interface GetWorkspacesPort {

    WorkspaceState getWorkspaceById(Long workspaceId);

    List<WorkspaceState> getWorkspacesByUserId(Long userId);
}

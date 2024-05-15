package com.finnect.workspace.application.port.in;

import com.finnect.workspace.domain.state.WorkspaceState;

import java.util.List;

public interface GetWorkspacesQuery {

    List<WorkspaceState> getWorkspaces(Long userId);
}

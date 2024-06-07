package com.finnect.workspace.application.port.in;

import com.finnect.workspace.domain.state.WorkspaceState;

import java.util.List;

public interface GetWorkspaceQuery {

    List<WorkspaceState> getWorkspaces(Long userId);
}

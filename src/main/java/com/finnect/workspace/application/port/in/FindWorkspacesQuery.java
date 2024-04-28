package com.finnect.workspace.application.port.in;

import com.finnect.workspace.WorkspaceState;

import java.util.List;

public interface FindWorkspacesQuery {
    List<WorkspaceState> findWorkspaces(Long userId);
}

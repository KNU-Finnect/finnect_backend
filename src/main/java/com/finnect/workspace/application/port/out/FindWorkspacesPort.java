package com.finnect.workspace.application.port.out;

import com.finnect.workspace.WorkspaceState;

import java.util.List;

public interface FindWorkspacesPort {

    List<WorkspaceState> findWorkspacesByUser(Long workspaceId);
}

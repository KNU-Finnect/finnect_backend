package com.finnect.workspace.application.port.in;

import com.finnect.workspace.domain.state.WorkspaceState;

public interface RenameWorkspaceUsecase {

    WorkspaceState renameWorkspace(RenameWorkspaceCommand cmd);
}

package com.finnect.workspace.application;

import com.finnect.workspace.domain.state.WorkspaceState;
import com.finnect.workspace.application.port.in.RenameWorkspaceCommand;
import com.finnect.workspace.application.port.in.RenameWorkspaceUsecase;
import com.finnect.workspace.application.port.out.UpdateWorkspacePort;
import com.finnect.workspace.domain.Workspace;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Getter
public class RenameWorkspaceService implements RenameWorkspaceUsecase {

    private final UpdateWorkspacePort updateWorkspacePort;

    @Override
    public WorkspaceState renameWorkspace(RenameWorkspaceCommand cmd) {
        Workspace workspace = new Workspace(cmd.getWorkspaceId(), cmd.getWorkspaceName());

        WorkspaceState savedState = updateWorkspacePort.updateWorkspace(workspace);

        return savedState;
    }
}

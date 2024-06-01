package com.finnect.workspace.application;

import com.finnect.user.application.port.in.CheckDefaultWorkspaceUsecase;
import com.finnect.user.application.port.in.ChangeDefaultWorkspaceUseCase;
import com.finnect.workspace.domain.state.WorkspaceState;
import com.finnect.workspace.application.port.in.CreateWorkspaceCommand;
import com.finnect.workspace.application.port.in.CreateWorkspaceUsecase;
import com.finnect.workspace.application.port.out.CreateWorkspacePort;
import com.finnect.workspace.domain.Workspace;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Slf4j
public class CreateWorkspaceService implements CreateWorkspaceUsecase {

    private final CreateWorkspacePort createWorkspacePort;
    private final CheckDefaultWorkspaceUsecase checkDefaultWorkspaceUsecase;
    private final ChangeDefaultWorkspaceUseCase changeDefaultWorkspaceUseCase;

    @Override
    public WorkspaceState createWorkspace(CreateWorkspaceCommand cmd) {
        boolean hasDefault = checkDefaultWorkspaceUsecase.checkDefaultWorkspace(cmd.getUserId());

        Workspace workspace = new Workspace(cmd.getWorkspaceName());

        WorkspaceState savedState = createWorkspacePort.createWorkspace(workspace);

        if (hasDefault)
            changeDefaultWorkspaceUseCase.setDefault(savedState.getWorkspaceId());

        return savedState;
    }
}

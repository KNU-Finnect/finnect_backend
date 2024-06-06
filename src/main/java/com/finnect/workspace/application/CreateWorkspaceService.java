package com.finnect.workspace.application;

import com.finnect.crm.application.port.in.column.CreateNewColumnUseCase;
import com.finnect.user.application.port.in.ChangeDefaultWorkspaceUseCase;
import com.finnect.user.application.port.in.CheckDefaultWorkspaceUseCase;
import com.finnect.user.application.port.in.GetNameUseCase;
import com.finnect.user.vo.UserId;
import com.finnect.user.vo.WorkspaceId;
import com.finnect.view.application.port.in.CreateViewUseCase;
import com.finnect.workspace.application.port.in.CreateMemberCommand;
import com.finnect.workspace.application.port.in.CreateMemberUsecase;
import com.finnect.workspace.domain.state.WorkspaceState;
import com.finnect.workspace.application.port.in.CreateWorkspaceCommand;
import com.finnect.workspace.application.port.in.CreateWorkspaceUsecase;
import com.finnect.workspace.application.port.out.CreateWorkspacePort;
import com.finnect.workspace.domain.Workspace;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Getter
@Slf4j
@Transactional
public class CreateWorkspaceService implements CreateWorkspaceUsecase {

    private final CreateWorkspacePort createWorkspacePort;
    private final ChangeDefaultWorkspaceUseCase changeDefaultWorkspaceUseCase;
    private final CheckDefaultWorkspaceUseCase checkDefaultWorkspaceUseCase;
    private final GetNameUseCase getNameUseCase;
    private final CreateMemberUsecase createMemberUsecase;
    private final CreateNewColumnUseCase createNewColumnUseCase;
    private final CreateViewUseCase createViewUseCase;

    @Override
    public WorkspaceState createWorkspace(CreateWorkspaceCommand cmd) {

        Workspace workspace = new Workspace(cmd.getWorkspaceName());
        WorkspaceState savedState = createWorkspacePort.createWorkspace(workspace);

        UserId userId = new UserId(cmd.getUserId());
        if (!checkDefaultWorkspaceUseCase.checkDefaultWorkspace(userId))
            changeDefaultWorkspaceUseCase.changeDefaultWorkspace(
                    userId,
                    new WorkspaceId(savedState.getWorkspaceId()));

        // 멤버 생성
        String name = getNameUseCase.getNameById(userId);
        createMemberUsecase.createMember(
                CreateMemberCommand.builder()
                        .userId(userId.value())
                        .workspaceId(savedState.getWorkspaceId())
                        .nickname(name)
                        .build()
        );
        //defaultView 생성
        createViewUseCase.createDefaultView(savedState.getWorkspaceId());

        // default column 생성
        createNewColumnUseCase.createDefaultColumn(savedState.getWorkspaceId());

        return savedState;
    }
}

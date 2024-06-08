package com.finnect.workspace.application;

import com.finnect.crm.application.port.in.column.CreateNewColumnUseCase;
import com.finnect.user.application.port.in.ChangeDefaultWorkspaceUseCase;
import com.finnect.user.application.port.in.CheckDefaultWorkspaceQuery;
import com.finnect.user.application.port.in.GetPersonalNameQuery;
import com.finnect.user.application.port.in.command.ChangeDefaultWorkspaceCommand;
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
@Transactional
@Slf4j
public class CreateWorkspaceService implements CreateWorkspaceUsecase {

    private final CreateWorkspacePort createWorkspacePort;
    private final ChangeDefaultWorkspaceUseCase changeDefaultWorkspaceUseCase;
    private final CheckDefaultWorkspaceQuery checkDefaultWorkspaceQuery;
    private final GetPersonalNameQuery getPersonalNameQuery;
    private final CreateMemberUsecase createMemberUsecase;
    private final CreateNewColumnUseCase createNewColumnUseCase;
    private final CreateViewUseCase createViewUseCase;

    @Override
    public WorkspaceState createWorkspace(CreateWorkspaceCommand cmd) {

        Workspace workspace = new Workspace(cmd.getWorkspaceName());
        WorkspaceState savedState = createWorkspacePort.createWorkspace(workspace);

        UserId userId = new UserId(cmd.getUserId());
        WorkspaceId workspaceId = new WorkspaceId(savedState.getWorkspaceId());

        if (!checkDefaultWorkspaceQuery.checkDefaultWorkspace(userId)) {
            changeDefaultWorkspaceUseCase.changeDefaultWorkspace(
                    ChangeDefaultWorkspaceCommand.builder()
                            .userId(userId)
                            .workspaceId(workspaceId)
                            .build()
            );
        }


        // 멤버 생성
        log.info("Step1");
        String name = getPersonalNameQuery.getPersonalName(userId);
        createMemberUsecase.createMember(
                CreateMemberCommand.builder()
                        .userId(userId.value())
                        .workspaceId(savedState.getWorkspaceId())
                        .nickname(name)
                        .build()
        );

        log.info("Step2");

        log.info(savedState.getWorkspaceId().toString());
        //defaultView 생성
        createViewUseCase.createDefaultView(savedState.getWorkspaceId());

        log.info("Step3");
        // default column 생성
        createNewColumnUseCase.createDefaultColumn(savedState.getWorkspaceId());

        return savedState;
    }
}

package com.finnect.workspace.application;

import com.finnect.user.application.port.in.ChangeDefaultWorkspaceUseCase;
import com.finnect.user.application.port.in.CheckDefaultWorkspaceQuery;
import com.finnect.workspace.domain.state.WorkspaceState;
import com.finnect.workspace.application.port.in.CreateWorkspaceCommand;
import com.finnect.workspace.application.port.out.CreateWorkspacePort;
import com.finnect.workspace.domain.Workspace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CreateWorkspaceServiceTest {
    @Mock CreateWorkspacePort createWorkspacePort;
    @Mock ChangeDefaultWorkspaceUseCase changeDefaultWorkspaceUseCase;
    @Mock CheckDefaultWorkspaceQuery checkDefaultWorkspaceQuery;

    @InjectMocks
    CreateWorkspaceService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("워크스페이스 생성 유스케이스")
    void createWithoutUserDomainTest() {
        // given
        String workspaceName = "given name2";
        Long userId = 1L;
        CreateWorkspaceCommand command = new CreateWorkspaceCommand(workspaceName, userId);


        // when
        when(createWorkspacePort.createWorkspace(any(WorkspaceState.class)))
                .thenReturn(new Workspace(1L, workspaceName));

        WorkspaceState state = service.createWorkspace(command);

        // then
        assertEquals(state.getWorkspaceName(), workspaceName);
    }
}

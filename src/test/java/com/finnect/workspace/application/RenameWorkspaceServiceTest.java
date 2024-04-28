package com.finnect.workspace.application;

import com.finnect.workspace.WorkspaceState;
import com.finnect.workspace.application.port.in.CreateWorkspaceCommand;
import com.finnect.workspace.application.port.in.RenameWorkspaceCommand;
import com.finnect.workspace.application.port.out.UpdateWorkspacePort;
import com.finnect.workspace.domain.Workspace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RenameWorkspaceServiceTest {

    @Mock
    UpdateWorkspacePort updateWorkspacePort;


    @InjectMocks
    RenameWorkspaceService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("워크스페이스 이름 변경 유스케이스")
    void renameWorkspaceTest() {
        // given
        Long workspaceId = 1L;
        String newWorkspaceName = "test new name";
        RenameWorkspaceCommand command = new RenameWorkspaceCommand(workspaceId, newWorkspaceName);

        // when
        when(updateWorkspacePort.updateWorkspace(any(WorkspaceState.class)))
                .thenReturn(new Workspace(1L, newWorkspaceName));

        WorkspaceState state = service.renameWorkspace(command);

        // then
        assertEquals(state.getWorkspaceId(), workspaceId);
        assertEquals(state.getWorkspaceName(), newWorkspaceName);

    }
}
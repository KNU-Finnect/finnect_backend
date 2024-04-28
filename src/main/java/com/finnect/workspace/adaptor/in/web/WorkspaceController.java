package com.finnect.workspace.adaptor.in.web;

import com.finnect.common.Response;
import com.finnect.workspace.WorkspaceState;
import com.finnect.workspace.adaptor.in.web.req.CreateWorkspaceRequest;
import com.finnect.workspace.adaptor.in.web.res.CreateWorkspaceResponse;
import com.finnect.workspace.adaptor.in.web.res.dto.WorkspaceDto;
import com.finnect.workspace.application.port.in.CreateWorkspaceCommand;
import com.finnect.workspace.application.port.in.CreateWorkspaceUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WorkspaceController {

    private final CreateWorkspaceUsecase createWorkspaceUsecase;

    @PostMapping("/workspaces")
    public ResponseEntity<Response<CreateWorkspaceResponse>> createWorkspace(@RequestBody CreateWorkspaceRequest request) {
        CreateWorkspaceCommand workspaceCommand = CreateWorkspaceCommand.builder()
                .workspaceName(request.getWorkspaceName())
                .userId(1L)
                .build();

        WorkspaceState state = createWorkspaceUsecase.createWorkspace(workspaceCommand);

        WorkspaceDto workspaceDto = new WorkspaceDto(state.getWorkspaceName());

        CreateWorkspaceResponse createWorkspaceResponse = new CreateWorkspaceResponse(workspaceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response(201, createWorkspaceResponse));
    }
}

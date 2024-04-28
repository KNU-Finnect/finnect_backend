package com.finnect.workspace.adaptor.in.web;

import com.finnect.common.Response;
import com.finnect.workspace.WorkspaceState;
import com.finnect.workspace.adaptor.in.web.req.CreateWorkspaceRequest;
import com.finnect.workspace.adaptor.in.web.req.RenameWorkspaceRequest;
import com.finnect.workspace.adaptor.in.web.res.CreateWorkspaceResponse;
import com.finnect.workspace.adaptor.in.web.res.RenameWorkspaceResponse;
import com.finnect.workspace.adaptor.in.web.res.dto.WorkspaceDto;
import com.finnect.workspace.application.port.in.CreateWorkspaceCommand;
import com.finnect.workspace.application.port.in.CreateWorkspaceUsecase;
import com.finnect.workspace.application.port.in.RenameWorkspaceCommand;
import com.finnect.workspace.application.port.in.RenameWorkspaceUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WorkspaceController {

    private final CreateWorkspaceUsecase createWorkspaceUsecase;
    private final RenameWorkspaceUsecase renameWorkspaceUsecase;

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

    @PutMapping("/workspaces")
    public ResponseEntity<Response<RenameWorkspaceResponse>> renameWorkspace(@RequestBody RenameWorkspaceRequest request) {
        RenameWorkspaceCommand renameCommand = RenameWorkspaceCommand.builder()
                .workspaceId(1L)
                .newName(request.getWorkspaceName())
                .build();

        WorkspaceState state = renameWorkspaceUsecase.renameWorkspace(renameCommand);

        WorkspaceDto workspaceDto = new WorkspaceDto(state.getWorkspaceName());
        RenameWorkspaceResponse renameWorkspaceResponse = new RenameWorkspaceResponse(workspaceDto);
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>(HttpStatus.OK.value(), renameWorkspaceResponse));
    }
}

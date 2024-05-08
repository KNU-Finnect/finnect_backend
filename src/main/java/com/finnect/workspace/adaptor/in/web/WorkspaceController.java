package com.finnect.workspace.adaptor.in.web;

import com.finnect.common.Response;
import com.finnect.workspace.WorkspaceState;
import com.finnect.workspace.adaptor.in.web.req.CreateWorkspaceRequest;
import com.finnect.workspace.adaptor.in.web.req.InviteMembersRequest;
import com.finnect.workspace.adaptor.in.web.req.RenameWorkspaceRequest;
import com.finnect.workspace.adaptor.in.web.res.CreateWorkspaceResponse;
import com.finnect.workspace.adaptor.in.web.res.InviteMembersResponse;
import com.finnect.workspace.adaptor.in.web.res.RenameWorkspaceResponse;
import com.finnect.workspace.adaptor.in.web.res.dto.InvitationDto;
import com.finnect.workspace.adaptor.in.web.res.dto.WorkspaceDto;
import com.finnect.workspace.application.port.in.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WorkspaceController {

    private final CreateWorkspaceUsecase createWorkspaceUsecase;
    private final RenameWorkspaceUsecase renameWorkspaceUsecase;
    private final InviteMembersUsecase inviteMembersUsecase;

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

    @PostMapping("/workspaces/invitation")
    public ResponseEntity<Response<InviteMembersResponse>> inviteMembers(@RequestBody InviteMembersRequest request) {
        List<InviteMembersCommand> cmds = request.getEmails()
                .stream()
                .map(InviteMembersCommand::new)
                .collect(Collectors.toList());

        List<InvitationDto> invitations = inviteMembersUsecase.inviteMembers(cmds)
                .stream()
                .map(InvitationDto::new)
                .collect(Collectors.toList());
        InviteMembersResponse inviteMembersResponse = new InviteMembersResponse(invitations);
        return ResponseEntity.status(HttpStatus.OK).body(
                new Response<>(HttpStatus.OK.value(), inviteMembersResponse)
        );
    }
}

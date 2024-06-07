package com.finnect.workspace.adaptor.in.web;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.user.application.port.in.FindUsernameUseCase;
import com.finnect.user.application.port.in.command.FindUsernameCommand;
import com.finnect.user.vo.WorkspaceAuthority;
import com.finnect.workspace.domain.state.WorkspaceState;
import com.finnect.workspace.adaptor.in.web.req.CreateWorkspaceRequest;
import com.finnect.workspace.adaptor.in.web.req.InviteMembersRequest;
import com.finnect.workspace.adaptor.in.web.req.RenameWorkspaceRequest;
import com.finnect.workspace.adaptor.in.web.res.CreateWorkspaceResponse;
import com.finnect.workspace.adaptor.in.web.res.GetWorkspacesResponse;
import com.finnect.workspace.adaptor.in.web.res.InviteMembersResponse;
import com.finnect.workspace.adaptor.in.web.res.RenameWorkspaceResponse;
import com.finnect.workspace.adaptor.in.web.res.dto.InvitationDto;
import com.finnect.workspace.adaptor.in.web.res.dto.WorkspaceDto;
import com.finnect.workspace.adaptor.in.web.res.dto.WorkspaceWithoutIdDto;
import com.finnect.workspace.application.port.in.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WorkspaceController {

    private final CreateWorkspaceUsecase createWorkspaceUsecase;
    private final RenameWorkspaceUsecase renameWorkspaceUsecase;
    private final InviteMembersUsecase inviteMembersUsecase;
    private final GetWorkspaceQuery getWorkspaceQuery;
    private final FindUsernameUseCase findUsernameUseCase;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/workspaces")
    public ResponseEntity<ApiResult<CreateWorkspaceResponse>> createWorkspace(@RequestBody CreateWorkspaceRequest request) {
        Long userId;
        try {
            userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
        } catch (Exception e) {
            throw new RuntimeException("토큰에 사용자 ID가 누락되었습니다.");
        }

        CreateWorkspaceCommand workspaceCommand = CreateWorkspaceCommand.builder()
                .workspaceName(request.getWorkspaceName())
                .userId(userId)
                .build();

        WorkspaceState state = createWorkspaceUsecase.createWorkspace(workspaceCommand);
        System.out.println("state.getWorkspaceName()");
        WorkspaceWithoutIdDto workspaceWithoutIdDto = new WorkspaceWithoutIdDto(state.getWorkspaceName());

        CreateWorkspaceResponse createWorkspaceResponse = new CreateWorkspaceResponse(workspaceWithoutIdDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiUtils.success(HttpStatus.CREATED, createWorkspaceResponse));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/workspaces")
    public ResponseEntity<ApiResult<RenameWorkspaceResponse>> renameWorkspace(@RequestBody RenameWorkspaceRequest request) {
        Long workspaceId;
        try {
            workspaceId = WorkspaceAuthority.from(
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities()
            ).workspaceId().value();
        } catch (Exception e) {
            throw new RuntimeException("워크스페이스 ID가 누락되었습니다.");
        }

        RenameWorkspaceCommand renameCommand = RenameWorkspaceCommand.builder()
                .workspaceId(workspaceId)
                .newName(request.getWorkspaceName())
                .build();

        WorkspaceState state = renameWorkspaceUsecase.renameWorkspace(renameCommand);

        WorkspaceWithoutIdDto workspaceWithoutIdDto = new WorkspaceWithoutIdDto(state.getWorkspaceName());
        RenameWorkspaceResponse renameWorkspaceResponse = new RenameWorkspaceResponse(workspaceWithoutIdDto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiUtils.success(HttpStatus.OK, renameWorkspaceResponse));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/workspaces")
    public ResponseEntity<ApiResult<GetWorkspacesResponse>> getWorkspaces() {
        Long userId;
        try {
            userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
        } catch (Exception e) {
            throw new RuntimeException("토큰에 사용자 ID가 누락되었습니다.");
        }

        List<WorkspaceDto> workspaceStates = getWorkspaceQuery.getWorkspaces(userId)
                .stream()
                .map(WorkspaceDto::new)
                .collect(Collectors.toList());
        GetWorkspacesResponse getWorkspacesResponse = new GetWorkspacesResponse(workspaceStates);
        return ResponseEntity.status(HttpStatus.OK).body(ApiUtils.success(HttpStatus.OK, getWorkspacesResponse));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/workspaces/invitation")
    public ResponseEntity<ApiResult<InviteMembersResponse>> inviteMembers(@RequestBody InviteMembersRequest request) {
        Long userId;
        try {
            userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
        } catch (Exception e) {
            throw new RuntimeException("토큰에 사용자 ID가 누락되었습니다.");
        }
        Long workspaceId;
        try {
            workspaceId = WorkspaceAuthority.from(
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities()
            ).workspaceId().value();
        } catch (Exception e) {
            throw new RuntimeException("워크스페이스 ID가 누락되었습니다.");
        }

        List<InviteMembersCommand> cmds = request.getEmails()
                .stream()
                .map((email) -> new InviteMembersCommand(email,
                        "임의의 이름",
                        getWorkspaceQuery.getWorkspace(workspaceId).getWorkspaceName()))
                .collect(Collectors.toList());

        List<InvitationDto> invitations = inviteMembersUsecase.inviteMembers(cmds)
                .stream()
                .map(InvitationDto::new)
                .collect(Collectors.toList());
        InviteMembersResponse inviteMembersResponse = new InviteMembersResponse(invitations);
        return ResponseEntity.status(HttpStatus.OK).body(
               ApiUtils.success(HttpStatus.OK, inviteMembersResponse)
        );
    }
}

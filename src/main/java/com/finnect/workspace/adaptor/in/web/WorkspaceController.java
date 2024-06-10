package com.finnect.workspace.adaptor.in.web;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Workspace 생성 API",
            description = """
                    주어진 access token의 user id와 workspace name으로 새로운 워크스페이스를 생성합니다.
                    워크스페이스가 생성될 때 Company와 Deal에 기본 Column 5종이 생성됩니다.
                    또한 생성한 사용자가 Member로 가입되고, 사용자에게 Default workspace가 없는 경우 생성된 워크스페이스로 설정됩니다.
                    """
    )
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

        WorkspaceWithoutIdDto workspaceWithoutIdDto = new WorkspaceWithoutIdDto(state.getWorkspaceName());
        CreateWorkspaceResponse createWorkspaceResponse = new CreateWorkspaceResponse(workspaceWithoutIdDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiUtils.success(HttpStatus.CREATED, createWorkspaceResponse));
    }

    @Operation(
            summary = "Workspace 이름 변경 API",
            description = "주어진 access token의 workspace id와 workspace name으로 워크스페이스의 이름을 변경합니다."
    )
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

    @Operation(
            summary = "Workspace 목록 조회",
            description = "주어진 access token의 user id로 사용자가 속한 워크스페이스의 목록을 반환합니다."
    )
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

    @Operation(
            summary = "Workspace로 초대",
            description = """
                    주어진 access token의 user id와 workspace id, 초대할 사용자의 이메일로 워크스페이스에 다른 사용자들을 초대합니다.
                    이미 가입한 사용자만 초대할 수 있습니다."""
    )
    @ApiResponse(
            responseCode = "200",
            description = """
                    result 값의 의미
                    - SUCCEED: 성공
                    - FAIL: 실패 (이메일은 정상이나 메일 서버 오류 등의 이유로 실패)
                    - ALREADY_MEMBER: 이미 해당 워크스페이스에 속한 사용자
                    - YET_SIGNUP: 회원가입하지 않은 이메일"""
    )
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
                .map((email) -> new InviteMembersCommand(email, userId, workspaceId))
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

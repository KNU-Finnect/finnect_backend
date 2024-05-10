package com.finnect.workspace.adaptor.in.web;

import com.finnect.common.Response;
import com.finnect.workspace.MemberState;
import com.finnect.workspace.adaptor.in.web.req.CreateMemberRequest;
import com.finnect.workspace.adaptor.in.web.res.CreateMemberResponse;
import com.finnect.workspace.adaptor.in.web.res.FindMembersResponse;
import com.finnect.workspace.adaptor.in.web.res.dto.MemberDto;
import com.finnect.workspace.adaptor.in.web.res.dto.MemberWithoutIdDto;
import com.finnect.workspace.application.port.in.CreateMemberCommand;
import com.finnect.workspace.application.port.in.CreateMemberUsecase;
import com.finnect.workspace.application.port.in.ExitWorkspaceUsecase;
import com.finnect.workspace.application.port.in.FindMembersQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final CreateMemberUsecase createMemberUsecase;
    private final FindMembersQuery findMembersQuery;
    private final ExitWorkspaceUsecase exitWorkspaceUsecase;

    @PostMapping("/workspaces/members")
    public ResponseEntity<Response<CreateMemberResponse>> createWorkspace(@RequestBody CreateMemberRequest request) {
        CreateMemberCommand memberCommand = CreateMemberCommand.builder()
                .userId(1L)
                .workspaceId(1L)
                .nickname(request.getNickname())
                .role(request.getRole())
                .phone(request.getPhone())
                .build();

        MemberState state = createMemberUsecase.createMember(memberCommand);

        MemberDto memberDto = new MemberDto(
                state.getUserId(),
                state.getNickname(),
                state.getRole(),
                state.getPhone()
        );
        CreateMemberResponse createMemberResponse = new CreateMemberResponse(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response(HttpStatus.OK.value(), createMemberResponse));
    }

    @GetMapping("/workspaces/members")
    public ResponseEntity<Response> findMembers() {
        Long workspaceId = 1L;

        List<MemberWithoutIdDto> members = findMembersQuery.loadMembersByWorkspace(workspaceId)
                .stream()
                .map(MemberWithoutIdDto::new)
                .collect(Collectors.toList());
        FindMembersResponse findMembersResponse = new FindMembersResponse(members);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK.value(), findMembersResponse));
    }

    @DeleteMapping("/workspaces/members")
    public ResponseEntity<Response> exitWorkspace() {
        Long userId = 1L;
        Long workspaceId = 1L;
        exitWorkspaceUsecase.exit(userId, workspaceId);

        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK.value(), null));
    }
}

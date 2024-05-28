package com.finnect.workspace.application;

import com.finnect.workspace.domain.state.MemberState;
import com.finnect.workspace.adaptor.out.persistence.MemberId;
import com.finnect.workspace.adaptor.out.persistence.MemberEntity;
import com.finnect.workspace.application.port.in.CreateMemberCommand;
import com.finnect.workspace.application.port.out.SaveMemberPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CrateMemberServiceTest {
    @Mock
    SaveMemberPort saveMemberPort;

    @InjectMocks
    CreateMemberService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("멤버 생성 유스케이스")
    void createWithoutUserDomainTest() {
        // given
        Long userId = 1L;
        Long workspaceId = 1L;
        String nickname = "test nickname";
        String role = "test role";
        String phone = "010-1234-5678";
        CreateMemberCommand command = new CreateMemberCommand(userId, workspaceId, nickname, role, phone);

        // when
        when(saveMemberPort.saveMember(any(MemberState.class)))
                .thenReturn(MemberEntity.builder()
                        .memberId(new MemberId(userId, workspaceId))
                        .nickname(nickname)
                        .role(role)
                        .phone(phone)
                        .build());

        MemberState state = service.createMember(command);

        // then
        assertEquals(state.getUserId(), userId);
        assertEquals(state.getWorkspaceId(), workspaceId);
        assertEquals(state.getNickname(), nickname);
        assertEquals(state.getRole(), role);
        assertEquals(state.getPhone(), phone);
    }
}

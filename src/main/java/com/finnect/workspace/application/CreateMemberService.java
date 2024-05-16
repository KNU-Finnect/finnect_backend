package com.finnect.workspace.application;

import com.finnect.workspace.domain.state.MemberState;
import com.finnect.workspace.application.port.in.CreateMemberCommand;
import com.finnect.workspace.application.port.in.CreateMemberUsecase;
import com.finnect.workspace.application.port.out.SaveMemberPort;
import com.finnect.workspace.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class CreateMemberService implements CreateMemberUsecase {

    private final SaveMemberPort saveMemberPort;

    @Override
    public MemberState createMember(CreateMemberCommand cmd) {

        Member member = new Member(
                cmd.getUserId(), cmd.getWorkspaceId(), cmd.getNickname(), cmd.getRole(), cmd.getPhone()
        );

        MemberState savedMember = saveMemberPort.saveMember(member);

        return savedMember;
    }
}

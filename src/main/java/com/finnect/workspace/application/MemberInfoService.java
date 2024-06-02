package com.finnect.workspace.application;

import com.finnect.workspace.application.port.in.UpdateMemberCommand;
import com.finnect.workspace.application.port.in.UpdateMemberUsecase;
import com.finnect.workspace.application.port.out.GetMemberPort;
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
public class MemberInfoService implements CreateMemberUsecase, UpdateMemberUsecase {

    private final SaveMemberPort saveMemberPort;
    private final GetMemberPort getMemberPort;

    @Override
    public MemberState createMember(CreateMemberCommand cmd) {

        Member member = new Member(
                cmd.getUserId(), cmd.getWorkspaceId(), cmd.getNickname(), "", ""
        );

        MemberState savedMember = saveMemberPort.saveMember(member);

        return savedMember;
    }

    @Override
    public MemberState updateMember(UpdateMemberCommand command) {
        Member member = Member.from(getMemberPort.getMember(command.getUserId(), command.getWorkspaceId()));

        member.update(command.getNickname(), command.getRole(), command.getPhone());

        return saveMemberPort.saveMember(member);
    }
}

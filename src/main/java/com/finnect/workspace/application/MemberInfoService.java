package com.finnect.workspace.application;

import com.finnect.common.error.CustomException;
import com.finnect.user.application.port.in.CheckUserExistsUseCase;
import com.finnect.user.vo.UserId;
import com.finnect.workspace.application.port.in.UpdateMemberCommand;
import com.finnect.workspace.application.port.in.UpdateMemberUsecase;
import com.finnect.workspace.application.port.out.CheckWorkspaceQuery;
import com.finnect.workspace.application.port.out.GetMemberPort;
import com.finnect.workspace.domain.state.MemberState;
import com.finnect.workspace.application.port.in.CreateMemberCommand;
import com.finnect.workspace.application.port.in.CreateMemberUsecase;
import com.finnect.workspace.application.port.out.SaveMemberPort;
import com.finnect.workspace.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Getter
public class MemberInfoService implements CreateMemberUsecase, UpdateMemberUsecase {

    private final SaveMemberPort saveMemberPort;
    private final GetMemberPort getMemberPort;
    private final CheckWorkspaceQuery checkWorkspaceQuery;
    private final CheckUserExistsUseCase checkUserExistsUseCase;

    @Override
    public MemberState createMember(CreateMemberCommand cmd) {

        if (!checkUserExistsUseCase.checkUserExists(UserId.parseOrNull(cmd.getUserId())))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ID " + cmd.getUserId() + "은/는 존재하지 않는 사용자입니다.");
        if (!checkWorkspaceQuery.checkWorkspaceExists(cmd.getWorkspaceId()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ID " + cmd.getUserId() + "은/는 존재하지 않는 워크스페이스입니다.");

        return saveMemberPort.saveMember(Member.toDamin(cmd));
    }

    @Override
    public MemberState updateMember(UpdateMemberCommand command) {
        Member member = Member.from(getMemberPort.getMember(command.getUserId(), command.getWorkspaceId()));

        member.update(command.getNickname(), command.getRole(), command.getPhone());

        return saveMemberPort.saveMember(member);
    }
}

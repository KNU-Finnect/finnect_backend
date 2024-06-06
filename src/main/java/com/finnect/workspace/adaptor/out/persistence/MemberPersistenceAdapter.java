package com.finnect.workspace.adaptor.out.persistence;

import com.finnect.workspace.application.port.out.SearchMemberPort;
import com.finnect.workspace.domain.state.MemberState;
import com.finnect.workspace.application.port.out.GetMemberPort;
import com.finnect.workspace.application.port.out.FindMembersPort;
import com.finnect.workspace.application.port.out.SaveMemberPort;
import com.finnect.common.error.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
class MemberPersistenceAdapter implements SaveMemberPort, FindMembersPort, GetMemberPort, SearchMemberPort {

    private final MemberRepository memberRepository;

    @Override
    public MemberState saveMember(MemberState memberState) {
        MemberEntity memberEntity = MemberEntity.builder()
                .memberId(
                        new MemberId(memberState.getUserId(), memberState.getWorkspaceId())
                ).nickname(memberState.getNickname())
                .role(memberState.getRole())
                .phone(memberState.getPhone())
                .build();

        MemberState savedState = memberRepository.save(memberEntity);

        return savedState;
    }

    @Override
    public List<MemberState> findMembersByWorkspace(Long workspaceId) {
        List<MemberState> states = memberRepository.findAllByMemberIdWorkspaceId(workspaceId);

        return states;
    }

    @Override
    public MemberState getMember(Long userId, Long workspaceId) {
        MemberId memberId = new MemberId(userId, workspaceId);
        MemberState memberState = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 멤버입니다."));

        return memberState;
    }

    @Override
    public boolean searchMember(Long userId, Long workspaceId) {
        MemberId memberId = new MemberId(userId, workspaceId);

        return memberRepository.findById(memberId).isPresent();
    }
}

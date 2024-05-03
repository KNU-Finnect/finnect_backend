package com.finnect.workspace.adaptor.out.persistence;

import com.finnect.workspace.MemberState;
import com.finnect.workspace.application.port.out.SaveMemberPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class MemberPersistenceAdapter implements SaveMemberPort {

    private final MemberRepository memberRepository;

    @Override
    public MemberState saveMember(MemberState memberState) {
        MemberJpaEntity memberJpaEntity = MemberJpaEntity.builder()
                .memberId(
                        new MemberId(memberState.getUserId(), memberState.getWorkspaceId())
                ).nickname(memberState.getNickname())
                .role(memberState.getRole())
                .phone(memberState.getPhone())
                .build();

        MemberState savedState = memberRepository.save(memberJpaEntity);

        return savedState;
    }
}

package com.finnect.workspace.adaptor.out.persistence;

import com.finnect.workspace.MemberState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, MemberId> {
    List<MemberState> findAllByMemberIdWorkspaceId(Long workspaceId);
}

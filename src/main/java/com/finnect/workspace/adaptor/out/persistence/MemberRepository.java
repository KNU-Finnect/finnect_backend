package com.finnect.workspace.adaptor.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberJpaEntity, MemberId> {
}

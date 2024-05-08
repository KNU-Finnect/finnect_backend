package com.finnect.workspace.adaptor.out.persistence;

import com.finnect.workspace.WorkspaceState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface WorkspaceRepository extends JpaRepository<WorkspaceJpaEntity, Long> {

    WorkspaceJpaEntity save(WorkspaceJpaEntity workspaceJpaEntity);

    Optional<WorkspaceJpaEntity> findById(Long workspaceId);

    @Query("select w from WorkspaceJpaEntity w join member m on w.workspaceId = m.memberId.workspaceId where m.memberId.userId = :uid")
    List<WorkspaceState> getAllByUserId(@Param("uid") Long userId);
}
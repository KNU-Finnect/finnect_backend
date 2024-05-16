package com.finnect.workspace.adaptor.out.persistence;

import com.finnect.workspace.domain.state.WorkspaceState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface WorkspaceRepository extends JpaRepository<WorkspaceEntity, Long> {

    WorkspaceEntity save(WorkspaceEntity workspaceEntity);

    Optional<WorkspaceEntity> findById(Long workspaceId);

    @Query("select w from WorkspaceEntity w join member m on w.workspaceId = m.memberId.workspaceId where m.memberId.userId = :uid")
    List<WorkspaceState> getAllByUserId(@Param("uid") Long userId);
}
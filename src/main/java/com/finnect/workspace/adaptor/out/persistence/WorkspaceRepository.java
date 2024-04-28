package com.finnect.workspace.adaptor.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface WorkspaceRepository extends JpaRepository<WorkspaceJpaEntity, Long> {

    WorkspaceJpaEntity save(WorkspaceJpaEntity workspaceJpaEntity);

    Optional<WorkspaceJpaEntity> findById(Long workspaceId);


}
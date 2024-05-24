package com.finnect.crm.adapter.out.persistence.column;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataColumnRepository extends JpaRepository<DataColumnEntity, Long> {
    List<DataColumnEntity> findDataColumnEntitiesByWorkspaceId(Long workspaceId);
}

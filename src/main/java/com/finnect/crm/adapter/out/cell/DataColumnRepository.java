package com.finnect.crm.adapter.out.cell;

import com.finnect.crm.adapter.out.cell.persistence.DataColumnEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataColumnRepository extends JpaRepository<DataColumnEntity, Long> {
    List<DataColumnEntity> findDataColumnEntitiesByWorkspaceId(Long workspaceId);
}

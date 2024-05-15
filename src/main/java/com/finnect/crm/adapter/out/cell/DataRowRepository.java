package com.finnect.crm.adapter.out.cell;

import com.finnect.crm.adapter.out.cell.persistence.DataRowEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DataRowRepository extends JpaRepository<DataRowEntity, Long> {
    @Query
    (value = """
            SELECT dr.*
            FROM data_row AS dr JOIN deal AS d
            ON d.data_row_id = dr.data_row_id
            WHERE d.workspace_id = :workspace_id
        """,
        nativeQuery = true
    )
    List<DataRowEntity> findDataRowEntitiesByWorkspaceId(@Param(value = "workspace_id") Long workspaceId);
}

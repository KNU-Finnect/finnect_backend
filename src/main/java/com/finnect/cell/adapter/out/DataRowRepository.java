package com.finnect.cell.adapter.out;

import com.finnect.cell.adapter.out.persistence.DataRowEntity;
import java.util.List;
import javax.swing.JPanel;
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

package com.finnect.crm.adapter.out.persistence.cell;

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
    List<DataRowEntity> findDealDataRowEntitiesByWorkspaceId(@Param(value = "workspace_id") Long workspaceId);

    @Query
            (value = """
            SELECT dr.*
            FROM data_row AS dr JOIN company AS c
            ON c.data_row_id = dr.data_row_id
            WHERE c.workspace_id = :workspace_id
        """,
                    nativeQuery = true
            )
    List<DataRowEntity> findCompanyDataRowEntitiesByWorkspaceId(@Param(value = "workspace_id") Long workspaceId);
}

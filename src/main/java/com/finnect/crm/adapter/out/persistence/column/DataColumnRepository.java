package com.finnect.crm.adapter.out.persistence.column;

import com.finnect.crm.domain.column.DataType;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataColumnRepository extends JpaRepository<DataColumnEntity, Long> {
    List<DataColumnEntity> findDataColumnEntitiesByWorkspaceId(Long workspaceId);

    @Query(value = """
        SELECT COUNT(dc)
        FROM data_column dc
        WHERE dc.workspaceId = :workspaceId AND dc.dType = :dataType
    """)
    int countDataColumnEntitiesByWorkspaceIdAndDType(@Param("workspaceId")Long workspaceId,
                                                     @Param("dataType") DataType dataType);

    @Query("select dc from data_column dc where dc.workspaceId = :workspaceId AND dc.dType = :dataType")
    List<DataColumnEntity> findAllByDType(@Param("workspaceId") Long workspaceId, @Param("dataType") DataType dataType);
}

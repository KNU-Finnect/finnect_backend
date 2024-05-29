package com.finnect.crm.adapter.out.persistence.cell;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCellRepository extends JpaRepository<DataCellEntity, CellId> {
    List<DataCellEntity> findDataCellEntitiesByCellIdDataRowId(Long rowId);

    List<DataCellEntity> findDataCellEntitiesByCellIdColumnId(Long columnId);
}
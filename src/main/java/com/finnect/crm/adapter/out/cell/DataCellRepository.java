package com.finnect.crm.adapter.out.cell;

import com.finnect.crm.adapter.out.cell.persistence.DataCellEntity;
import com.finnect.crm.adapter.out.cell.persistence.CellIdEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCellRepository extends JpaRepository<DataCellEntity, CellIdEntity> {
    List<DataCellEntity> findDataCellEntitiesByCellIdDataRowId(Long rowId);
}
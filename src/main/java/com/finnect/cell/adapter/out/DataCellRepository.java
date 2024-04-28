package com.finnect.cell.adapter.out;

import com.finnect.cell.adapter.out.persistence.DataCellEntity;
import com.finnect.cell.adapter.out.persistence.CellId;
import com.finnect.cell.adapter.out.persistence.DataRowEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCellRepository extends JpaRepository<DataCellEntity, CellId> {
    List<DataCellEntity> findDataCellEntitiesByCellIdRowId(Long rowId);
}

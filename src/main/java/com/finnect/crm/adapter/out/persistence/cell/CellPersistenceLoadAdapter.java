package com.finnect.crm.adapter.out.persistence.cell;

import com.finnect.crm.application.port.out.cell.LoadDataCellPort;
import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.cell.state.DataCellState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
class CellPersistenceLoadAdapter implements LoadDataCellPort {
    private final DataCellRepository dataCellRepository;

    @Override
    public List<DataCell> loadDataCellsByRowId(DataCellState dataCellState) {
        List<DataCellEntity> dataCellEntities = dataCellRepository
                .findDataCellEntitiesByCellIdDataRowId(dataCellState.getRowId());
        return dataCellEntities
                .stream()
                .map(DataCellEntity::toDomain)
                .toList();
    }

    @Override
    public List<DataCell> loadDataCellsByColumnId(DataCellState dataCellState) {
        List<DataCellEntity> dataCellEntities = dataCellRepository
                .findDataCellEntitiesByCellIdColumnId(dataCellState.getColumnId());
        return dataCellEntities
                .stream()
                .map(DataCellEntity::toDomain)
                .toList();
    }

}

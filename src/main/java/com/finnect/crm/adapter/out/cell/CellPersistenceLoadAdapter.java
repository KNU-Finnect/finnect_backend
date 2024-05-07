package com.finnect.crm.adapter.out.cell;

import com.finnect.crm.adapter.out.cell.persistence.DataCellEntity;
import com.finnect.crm.adapter.out.cell.persistence.DataColumnEntity;
import com.finnect.crm.application.port.out.cell.LoadDataCellPort;
import com.finnect.crm.application.port.out.cell.LoadDataColumnPort;
import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.cell.state.DataCellState;
import com.finnect.crm.domain.cell.state.DataColumnState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CellPersistenceLoadAdapter implements LoadDataCellPort, LoadDataColumnPort {
    private final DataCellRepository dataCellRepository;
    private final DataColumnRepository dataColumnRepository;
    private final DataRowRepository dataRowRepository;

    @Override
    public List<DataCell> loadDataCellsByRowId(DataCellState dataCellState) {
        List<DataCellEntity> dataCellEntities = dataCellRepository
                .findDataCellEntitiesByCellIdRowId(dataCellState.getRowId());
        return dataCellEntities
                .stream()
                .map(DataCellEntity::toDomain)
                .toList();
    }

    @Override
    public List<DataColumn> loadDataColumnsByWorkspaceId(DataColumnState dataColumnState) {
        List<DataColumnEntity> dataColumns = dataColumnRepository
                .findDataColumnEntitiesByWorkspaceId(dataColumnState.getWorkspaceId());
        return dataColumns
                .stream()
                .map(DataColumnEntity::toDomain)
                .toList();
    }
}

package com.finnect.cell.adapter.out;

import com.finnect.cell.adapter.out.persistence.DataCellEntity;
import com.finnect.cell.adapter.out.persistence.DataColumnEntity;
import com.finnect.cell.application.port.out.LoadDataCellPort;
import com.finnect.cell.application.port.out.LoadDataColumnPort;
import com.finnect.cell.domain.DataColumn;
import com.finnect.cell.domain.state.DataCell;
import com.finnect.cell.domain.state.DataCellState;
import com.finnect.cell.domain.state.DataColumnState;
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

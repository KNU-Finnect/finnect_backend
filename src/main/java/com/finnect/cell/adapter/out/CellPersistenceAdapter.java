package com.finnect.cell.adapter.out;

import com.finnect.cell.adapter.out.persistence.CellEntity;
import com.finnect.cell.adapter.out.persistence.DataColumnEntity;
import com.finnect.cell.adapter.out.persistence.DataRowEntity;
import com.finnect.cell.application.port.out.SaveDataRowPort;
import com.finnect.cell.application.port.out.SaveNewCellUsePort;
import com.finnect.cell.domain.DataRow;
import com.finnect.cell.domain.state.ColumnState;
import com.finnect.cell.domain.state.DataRowState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
@Slf4j
public class CellPersistenceAdapter implements SaveDataRowPort, SaveNewCellUsePort {
    private final CellRepository cellRepository;
    private final ColumnRepository columnRepository;
    private final DataRowRepository dataRowRepository;
    @Override
    @Transactional
    public DataRow createNewDataRow() {
        DataRowEntity dataRowEntity = new DataRowEntity();
        dataRowRepository.save(dataRowEntity);
        log.info(dataRowEntity.getDataRowId().toString());

        return dataRowEntity.toDomain();
    }

    @Override
    public void saveNewCell(ColumnState column, DataRowState dataRow) {
        List<DataColumnEntity> columnEntities = columnRepository.findDataColumnEntitiesByWorkspaceId(column.getWorkspaceId());
        log.info(columnEntities.toString());
        List<CellEntity> cellEntities = columnEntities.stream()
                .map(dataColumnEntity -> CellEntity.builder()
                        .columnId(dataColumnEntity.getColumnId())
                        .rowId(dataRow.getDataRowId())
                        .build())
                .toList();
        log.info("Saving New Cell");
        cellRepository.saveAll(cellEntities);
    }
}

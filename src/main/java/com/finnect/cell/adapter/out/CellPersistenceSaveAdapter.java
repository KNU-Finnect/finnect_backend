package com.finnect.cell.adapter.out;

import com.finnect.cell.adapter.out.persistence.DataCellEntity;
import com.finnect.cell.adapter.out.persistence.DataColumnEntity;
import com.finnect.cell.adapter.out.persistence.DataRowEntity;
import com.finnect.cell.application.port.out.SaveDataColumnPort;
import com.finnect.cell.application.port.out.SaveDataRowPort;
import com.finnect.cell.application.port.out.SaveCellPort;
import com.finnect.cell.domain.DataColumn;
import com.finnect.cell.domain.DataRow;
import com.finnect.cell.domain.state.DataColumnState;
import com.finnect.cell.domain.state.DataRowState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
@Slf4j
public class CellPersistenceSaveAdapter implements SaveDataRowPort, SaveCellPort, SaveDataColumnPort {
    private final DataCellRepository dataCellRepository;
    private final DataColumnRepository dataColumnRepository;
    private final DataRowRepository dataRowRepository;
    @Override
    @Transactional
    public DataRow saveNewDataRow() {
        DataRowEntity dataRowEntity = new DataRowEntity();
        dataRowRepository.save(dataRowEntity);
        log.info(dataRowEntity.getDataRowId().toString());

        return dataRowEntity.toDomain();
    }
    @Override
    public DataColumn saveNewColumn(DataColumnState column) {
        DataColumnEntity dataColumnEntity = DataColumnEntity.toEntity(column);
        dataColumnRepository.save(dataColumnEntity);
        return dataColumnEntity.toDomain();
    }
    @Override
    public void saveNewCellByNewColumn(DataColumnState column) {
        log.info(column.getWorkspaceId().toString());

        List<DataRowEntity> dataRows = dataRowRepository.findDataRowEntitiesByWorkspaceId(column.getWorkspaceId());
        log.info(dataRows.toString());
        List<DataCellEntity> cellEntities = dataRows.stream()
                        .map(dataRowEntity -> DataCellEntity.builder()
                                .columnId(column.getColumnId())
                                .rowId(dataRowEntity.getDataRowId())
                                .build())
                        .toList();
        dataCellRepository.saveAll(cellEntities);
    }

    @Override
    public void saveNewCellByNewRow(DataColumnState column, DataRowState dataRow) {
        List<DataColumnEntity> columnEntities = dataColumnRepository.findDataColumnEntitiesByWorkspaceId(column.getWorkspaceId());
        log.info(columnEntities.toString());
        List<DataCellEntity> cellEntities = columnEntities.stream()
                .map(dataColumnEntity -> DataCellEntity.builder()
                        .columnId(dataColumnEntity.getColumnId())
                        .rowId(dataRow.getDataRowId())
                        .build())
                .toList();
        log.info("Saving New Cell");
        dataCellRepository.saveAll(cellEntities);
    }


}

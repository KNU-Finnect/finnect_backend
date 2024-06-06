package com.finnect.crm.adapter.out.persistence.column;

import com.finnect.crm.application.port.out.column.SaveDataColumnPort;
import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.state.DataColumnState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class ColumnPersistenceSaveAdapter implements SaveDataColumnPort {

    private final DataColumnRepository dataColumnRepository;
    @Override
    public DataColumn saveColumn(DataColumnState column) {
        DataColumnEntity dataColumnEntity = DataColumnEntity.toEntity(column);
        dataColumnRepository.save(dataColumnEntity);
        return dataColumnEntity.toDomain();
    }

    @Override
    public void saveColumns(List<DataColumnState> columns) {
        List<DataColumnEntity> dataColumnEntities = columns.stream()
                                                    .map(DataColumnEntity::toEntity)
                                                    .toList();
        dataColumnRepository.saveAll(dataColumnEntities);
    }
}

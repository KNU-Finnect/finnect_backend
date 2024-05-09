package com.finnect.crm.adapter.out.column;

import com.finnect.crm.adapter.out.column.persistence.DataColumnEntity;
import com.finnect.crm.application.port.out.column.LoadDataColumnPort;
import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.cell.state.DataColumnState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ColumnPersistenceLoadAdapter implements LoadDataColumnPort {
    private final DataColumnRepository dataColumnRepository;
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

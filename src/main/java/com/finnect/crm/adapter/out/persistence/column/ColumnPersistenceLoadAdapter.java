package com.finnect.crm.adapter.out.persistence.column;

import com.finnect.crm.application.port.out.column.LoadColumnCountPort;
import com.finnect.crm.application.port.out.column.LoadDataColumnPort;
import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.crm.domain.column.DataType;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class ColumnPersistenceLoadAdapter implements LoadDataColumnPort, LoadColumnCountPort {
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

    @Override
    public DataColumn loadDataColumnByColumnId(DataColumnState dataColumnState) {
        return dataColumnRepository.findById(dataColumnState.getColumnId())
                .orElseThrow(() -> new IllegalArgumentException("없는 DataColumnId 입니다."))
                .toDomain();
    }

    @Override
    public int loadDealColumnCount(Long workspaceId) {
        return dataColumnRepository.countDataColumnEntitiesByWorkspaceIdAndDType(workspaceId, DataType.DEAL);
    }

    @Override
    public List<DataColumnState> loadDataColumnsOfCompany(Long workspaceId) {
        return new ArrayList<>(dataColumnRepository.findAllByDType(workspaceId, DataType.COMPANY));
    }
}

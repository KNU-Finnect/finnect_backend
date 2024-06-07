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
    public List<DataColumn> loadDataColumnsOfDeal(Long workspaceId) {
        List<DataColumnEntity> dataColumns = dataColumnRepository
                .findAllByDType(workspaceId, DataType.DEAL);
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
    public List<DataColumn> loadDataColumnsOfCompany(Long workspaceId) {
        return dataColumnRepository.findAllByDType(workspaceId, DataType.COMPANY)
                .stream()
                .map(DataColumnEntity::toDomain)
                .toList();
    }

    @Override
    public int loadDealColumnCount(Long workspaceId) {
        return dataColumnRepository.countDataColumnEntitiesByWorkspaceIdAndDType(workspaceId, DataType.DEAL);
    }

    @Override
    public int loadCompanyColumnCount(Long workspaceId) {
        return dataColumnRepository.countDataColumnEntitiesByWorkspaceIdAndDType(workspaceId, DataType.COMPANY);
    }
}

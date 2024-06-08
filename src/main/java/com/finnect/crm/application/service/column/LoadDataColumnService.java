package com.finnect.crm.application.service.column;

import com.finnect.crm.application.port.in.column.LoadDataColumnUseCase;
import com.finnect.crm.application.port.out.column.LoadDataColumnPort;
import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.DataType;
import com.finnect.crm.domain.column.state.DataColumnState;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoadDataColumnService implements LoadDataColumnUseCase {
    private final LoadDataColumnPort dataColumnPort;

    @Override
    public List<DataColumnState> loadDataColumns(Long workspaceId, DataType dType) {
        List<DataColumn> dataColumns;

        if (dType == DataType.COMPANY) {
            dataColumns = dataColumnPort.loadDataColumnsOfCompany(workspaceId);
        } else if (dType == DataType.DEAL) {
            dataColumns = dataColumnPort.loadDataColumnsOfDeal(workspaceId);
        } else {
            throw new IllegalArgumentException("dType");
        }

        return dataColumns.stream()
                .map(DataColumnState.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public List<DataColumnState> loadDataColumns(DataColumn dataColumn) {
        List<DataColumn> dataColumns = dataColumnPort.loadDataColumnsOfDeal(dataColumn.getWorkspaceId());
        log.info(dataColumns.toString());
        return new ArrayList<>(dataColumns);
    }
}

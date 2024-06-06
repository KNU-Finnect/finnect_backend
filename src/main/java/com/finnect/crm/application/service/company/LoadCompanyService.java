package com.finnect.crm.application.service.company;

import com.finnect.crm.adapter.out.persistence.cell.CellId;
import com.finnect.crm.application.port.in.company.LoadCompanyUseCase;
import com.finnect.crm.application.port.out.cell.LoadDataCellPort;
import com.finnect.crm.application.port.out.column.LoadDataColumnPort;
import com.finnect.crm.application.port.out.company.LoadCompanyPort;
import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.column.ColumnType;
import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.crm.domain.company.CompanyDetail;
import com.finnect.crm.domain.company.CompanyState;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoadCompanyService implements LoadCompanyUseCase {
    private final LoadCompanyPort loadCompanyPort;
    private final LoadDataColumnPort loadDataColumnPort;
    private final LoadDataCellPort loadDataCellPort;

    @Override
    public List<CompanyState> loadCompaniesByWorkspaceId(Long workspaceId) {

        return loadCompanyPort.loadCompaniesByWorkspaceId(workspaceId);
    }

    @Override
    public CompanyDetail loadCompanyDetail(Long companyId) {
        CompanyState loadedState = loadCompanyPort.loadById(companyId);

        List<DataColumnState> columnStates = loadDataColumnPort.loadDataColumnsOfCompany();
        Map<Long, String> columnNameMap = columnStates.stream()
                .collect(Collectors.toMap(DataColumnState::getColumnId, DataColumnState::getColumnName));
        Map<Long, ColumnType> columnTypeMap = columnStates.stream()
                .collect(Collectors.toMap(DataColumnState::getColumnId, DataColumnState::getColumnType));

        List<DataCell> cells = loadDataCellPort.loadDataCellsByRowId(
                        DataCell.builder()
                                .cellId(new CellId(loadedState.getDataRowId(), null))
                                .build()
                );


        return CompanyDetail.of(
                loadedState,
                cells,
                columnNameMap,
                columnTypeMap
        );
    }
}

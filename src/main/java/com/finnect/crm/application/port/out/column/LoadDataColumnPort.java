package com.finnect.crm.application.port.out.column;

import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.state.DataColumnState;
import java.util.List;

public interface LoadDataColumnPort {

    List<DataColumn> loadDataColumnsByWorkspaceId (DataColumnState dataColumnState);

    DataColumn loadDataColumnByColumnId(DataColumnState dataColumnState);

    List<DataColumnState> loadDataColumnsOfCompany(Long workspaceId);
}

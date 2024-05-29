package com.finnect.crm.application.port.out.column;

import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.cell.state.DataColumnState;
import java.util.List;

public interface LoadDataColumnPort {

    List<DataColumn> loadDataColumnsByWorkspaceId (DataColumnState dataColumnState);

    DataColumn loadDataColumnByColumnId(DataColumnState dataColumnState);
}

package com.finnect.cell.application.port.out;

import com.finnect.cell.domain.DataColumn;
import com.finnect.cell.domain.state.DataCell;
import com.finnect.cell.domain.state.DataCellState;
import com.finnect.cell.domain.state.DataColumnState;
import java.util.List;

public interface LoadDataColumnPort {

    List<DataColumn> loadDataColumnsByWorkspaceId (DataColumnState dataColumnState);
}

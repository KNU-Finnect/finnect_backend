package com.finnect.cell.application.port.in;

import com.finnect.cell.domain.DataColumn;
import com.finnect.cell.domain.state.DataCell;
import com.finnect.cell.domain.state.DataCellState;
import com.finnect.cell.domain.state.DataColumnState;
import java.util.List;

public interface LoadDataCellUseCase {
    List<DataCellState> loadDataCells(DataCell dataCell);
}

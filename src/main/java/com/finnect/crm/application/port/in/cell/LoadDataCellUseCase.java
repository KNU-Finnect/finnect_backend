package com.finnect.crm.application.port.in.cell;

import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.cell.state.DataCellState;
import java.util.List;

public interface LoadDataCellUseCase {
    List<DataCellState> loadDataCells(DataCell dataCell);
}

package com.finnect.cell.application.port.out;

import com.finnect.cell.domain.state.DataCell;
import com.finnect.cell.domain.state.DataCellState;
import java.util.List;

public interface LoadDataCellPort {

    List<DataCell> loadDataCellsByRowId(DataCellState dataCellState);
}

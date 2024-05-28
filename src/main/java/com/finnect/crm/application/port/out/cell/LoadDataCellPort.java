package com.finnect.crm.application.port.out.cell;

import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.cell.state.DataCellState;
import java.util.List;

public interface LoadDataCellPort {

    List<DataCell> loadDataCellsByRowId(DataCellState dataCellState);
}

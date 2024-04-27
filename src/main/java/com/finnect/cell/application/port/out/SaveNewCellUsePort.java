package com.finnect.cell.application.port.out;

import com.finnect.cell.domain.state.ColumnState;
import com.finnect.cell.domain.state.DataRowState;

public interface SaveNewCellUsePort {

    void saveNewCell(ColumnState column, DataRowState dataRow);
}

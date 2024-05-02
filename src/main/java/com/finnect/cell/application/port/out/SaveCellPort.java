package com.finnect.cell.application.port.out;

import com.finnect.cell.domain.state.DataColumnState;
import com.finnect.cell.domain.state.DataRowState;

public interface SaveCellPort {

    void saveNewCellByNewRow(DataColumnState column, DataRowState dataRow);

    void saveNewCellByNewColumn(DataColumnState column);
}

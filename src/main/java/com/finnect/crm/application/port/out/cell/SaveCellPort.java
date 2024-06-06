package com.finnect.crm.application.port.out.cell;

import com.finnect.crm.domain.cell.state.DataCellState;
import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.crm.domain.cell.state.DataRowState;
import java.util.List;

public interface SaveCellPort {

    void saveNewCellByNewRow(DataColumnState column, DataRowState dataRow);

    void saveNewCellByNewColumn(DataColumnState column);


    void saveDataCells(List<DataCellState> dataCells);


    void saveDataCell(DataCellState dataCell);
}

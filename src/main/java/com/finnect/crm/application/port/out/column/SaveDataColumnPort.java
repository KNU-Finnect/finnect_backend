package com.finnect.crm.application.port.out.column;

import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.cell.state.DataColumnState;

public interface SaveDataColumnPort {
    DataColumn saveNewColumn(DataColumnState column);
}

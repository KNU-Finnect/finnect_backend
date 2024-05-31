package com.finnect.crm.application.port.out.column;

import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.state.DataColumnState;

public interface SaveDataColumnPort {
    DataColumn saveColumn(DataColumnState column);
}

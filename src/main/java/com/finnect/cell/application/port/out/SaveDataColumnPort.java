package com.finnect.cell.application.port.out;

import com.finnect.cell.adapter.out.persistence.DataColumnEntity;
import com.finnect.cell.domain.DataColumn;
import com.finnect.cell.domain.state.DataColumnState;

public interface SaveDataColumnPort {
    DataColumn saveNewColumn(DataColumnState column);
}

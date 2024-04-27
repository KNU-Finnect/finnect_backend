package com.finnect.cell.application.port.out;

import com.finnect.cell.domain.DataRow;
import com.finnect.cell.domain.state.DataColumnState;

public interface SaveDataRowPort {

    DataRow saveNewDataRow();
}

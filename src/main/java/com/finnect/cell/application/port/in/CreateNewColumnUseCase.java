package com.finnect.cell.application.port.in;

import com.finnect.cell.domain.DataColumn;
import com.finnect.cell.domain.state.DataColumnState;

public interface CreateNewColumnUseCase {

    DataColumnState createNewColumn(DataColumn dataColumn);
}

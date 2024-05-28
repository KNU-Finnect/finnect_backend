package com.finnect.crm.application.port.in.cell;

import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.cell.state.DataColumnState;

public interface CreateNewColumnUseCase {

    DataColumnState createNewColumn(DataColumn dataColumn);
}

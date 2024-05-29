package com.finnect.crm.application.port.in.column;

import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.cell.state.DataColumnState;

public interface ModifyColumnUseCase {

    DataColumnState modifyColumnInfo(DataColumn before);
}

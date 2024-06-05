package com.finnect.crm.application.port.in.column;

import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.state.DataColumnState;

public interface CreateNewColumnUseCase {

    DataColumnState createNewColumn(DataColumn dataColumn);

    void createDefaultColumn(Long workspaceId);
}

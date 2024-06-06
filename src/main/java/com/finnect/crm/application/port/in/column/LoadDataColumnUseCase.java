package com.finnect.crm.application.port.in.column;

import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.state.DataColumnState;
import java.util.List;

public interface LoadDataColumnUseCase {

    List<DataColumnState> loadDataColumns(DataColumn dataColumn);
}

package com.finnect.cell.application.port.in;

import com.finnect.cell.domain.DataColumn;
import com.finnect.cell.domain.state.DataColumnState;
import java.util.List;

public interface LoadDataColumnUseCase {

    List<DataColumnState> loadDataColumns(DataColumn dataColumn);
}

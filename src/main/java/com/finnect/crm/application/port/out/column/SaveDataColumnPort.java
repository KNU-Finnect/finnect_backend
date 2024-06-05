package com.finnect.crm.application.port.out.column;

import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.state.DataColumnState;
import java.util.List;

public interface SaveDataColumnPort {
    DataColumn saveColumn(DataColumnState column);

    void saveColumns(List<DataColumnState> columns);
}

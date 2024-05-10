package com.finnect.crm.domain.cell.state;

import com.finnect.crm.domain.cell.DataColumn.DataType;
import com.finnect.crm.domain.column.ColumnType;

public interface DataColumnState {

    Long getColumnId();
    Long getWorkspaceId();
    String getColumnName();
    ColumnType getColumnType();
    Double getColumnIndex();
    Boolean isHided();
    DataType getDType();

}

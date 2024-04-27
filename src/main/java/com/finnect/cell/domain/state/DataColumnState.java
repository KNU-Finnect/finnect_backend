package com.finnect.cell.domain.state;

import com.finnect.cell.domain.DataColumn.ColumnType;
import com.finnect.cell.domain.DataColumn.DataType;

public interface DataColumnState {

    Long getColumnId();
    Long getWorkspaceId();
    String getColumnName();
    ColumnType getColumnType();
    Double getColumnIndex();
    Boolean isHided();
    DataType getDType();

}

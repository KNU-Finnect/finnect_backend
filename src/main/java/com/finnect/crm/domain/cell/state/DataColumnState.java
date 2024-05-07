package com.finnect.crm.domain.cell.state;

import com.finnect.crm.domain.cell.DataColumn.ColumnType;
import com.finnect.crm.domain.cell.DataColumn.DataType;

public interface DataColumnState {

    Long getColumnId();
    Long getWorkspaceId();
    String getColumnName();
    ColumnType getColumnType();
    Double getColumnIndex();
    Boolean isHided();
    DataType getDType();

}

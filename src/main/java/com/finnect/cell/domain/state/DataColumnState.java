package com.finnect.cell.domain.state;

public interface ColumnState {

    Long getColumnId();
    Long getWorkspaceId();
    String getColumnName();
    String getColumnType();
    Double getColumnIndex();
    Boolean isHided();
    String getDType();

}

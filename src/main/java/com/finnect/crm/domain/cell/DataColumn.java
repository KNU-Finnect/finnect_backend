package com.finnect.crm.domain.cell;

import com.finnect.crm.domain.cell.state.DataColumnState;
import com.finnect.crm.domain.column.ColumnType;
import com.finnect.crm.domain.column.DataType;
import lombok.Builder;

public class DataColumn implements DataColumnState {
    private Long columnId;
    private Long workspaceId;
    private String columnName;
    private DataType dType;
    private ColumnType columnType;
    private Double columnIndex;
    private Boolean isHided;


    @Builder
    public DataColumn(Long columnId, Long workspaceId, String columnName, DataType dType, ColumnType columnType,
                      Double columnIndex, Boolean isHided) {
        this.columnId = columnId;
        this.workspaceId = workspaceId;
        this.columnName = columnName;
        this.dType = dType;
        this.columnType = columnType;
        this.columnIndex = columnIndex;
        this.isHided = isHided;
    }

    @Override
    public Long getColumnId() {
        return this.columnId;
    }
    @Override
    public Long getWorkspaceId() {
        return this.workspaceId;
    }
    @Override
    public String getColumnName() {
        return this.columnName;
    }
    @Override
    public ColumnType getColumnType() {
        return this.columnType;
    }
    @Override
    public Double getColumnIndex() {
        return columnIndex;
    }
    @Override
    public Boolean isHided() {
        return isHided;
    }
    @Override
    public DataType getDType() {
        return this.dType;
    }

    public void modifyColumnInfo(DataColumn before){
        this.columnId = before.getColumnId();
        this.dType = before.getDType();
        this.workspaceId = before.getWorkspaceId();
    }
    @Override
    public String toString() {
        return "DataColumn{" +
                "columnId=" + columnId +
                ", workspaceId=" + workspaceId +
                ", columnName='" + columnName + '\'' +
                ", dType=" + dType +
                ", columnType=" + columnType +
                ", columnIndex=" + columnIndex +
                ", isHided=" + isHided +
                '}';
    }
}

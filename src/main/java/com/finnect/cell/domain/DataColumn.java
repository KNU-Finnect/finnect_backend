package com.finnect.cell.domain;

import com.finnect.cell.domain.state.ColumnState;
import lombok.Builder;

public class Column implements ColumnState {
    private Long columnId;
    private Long workspaceId;
    private String columnName;
    private DataType dType;
    private ColumnType columnType;
    private Double columnIndex;
    private Boolean isHided;


    @Builder
    public Column(Long columnId, Long workspaceId, String columnName, DataType dType, ColumnType columnType,
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
    public String getColumnType() {
        return this.columnType.getType();
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
    public String getDType() {
        return this.dType.name();
    }

    public enum ColumnType {
        TEXT("TEXT"), NUMBER("NUMBER");
        private final String type;
        ColumnType(String str) {
            this.type = str;
        }

        public String getType() {
            return type;
        }
    }

    public enum DataType{
        DEAL, COMPANY;
    }
}

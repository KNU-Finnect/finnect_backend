package com.finnect.crm.domain.cell;

import com.finnect.crm.domain.cell.state.DataColumnState;
import lombok.Builder;
import lombok.Getter;

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

    public enum ColumnType {
        TEXT("TEXT"), NUMBER("NUMBER");

        @Getter
        private final String type;

        ColumnType(String str) {
            this.type = str;
        }

        public static ColumnType getColumnType(String columnType) {
            for(ColumnType type : ColumnType.values()){
                if(type.getType().equals(columnType)){
                    return type;
                }
            }
            return null;
        }
    }

    public enum DataType{
        DEAL("DEAL"), COMPANY("COMPANY");

        @Getter
        private final String type;
        DataType(String type) {
            this.type = type;
        }

        public static DataType getDataType(String dataType){
            for(DataType type : DataType.values()){
                if(type.getType().equals(dataType)){
                    return type;
                }
            }
            return null;
        }
    }
}

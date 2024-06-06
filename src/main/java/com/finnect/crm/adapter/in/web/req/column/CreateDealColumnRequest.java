package com.finnect.crm.adapter.in.web.req.column;

import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.ColumnType;
import com.finnect.crm.domain.column.DataType;
import lombok.Getter;


@Getter
public class CreateDealColumnRequest  {
    private String columnName;
    private String dType;
    private String columnType;
    private Double columnIndex;
    private Boolean isHided;

    public CreateDealColumnRequest(String columnName, String dType, String columnType,
                                   Double columnIndex, Boolean isHided) {
        this.columnName = columnName;
        this.dType = dType;
        this.columnType = columnType;
        this.columnIndex = columnIndex;
        this.isHided = isHided;
    }

    public DataColumn toDomain(Long workspaceId){
        DataType dataType = checkDataTypeValue();
        ColumnType columnType = this.checkColumnTypeValue();

        return DataColumn.builder()
                .columnId(null)
                .workspaceId(workspaceId)
                .columnName(this.columnName)
                .dType(dataType)
                .columnType(columnType)
                .columnIndex(this.columnIndex)
                .isHided(this.isHided)
                .build();
    }
    @Override
    public String toString() {
        return "CreateDealColumnRequest{" +
                ", columnName='" + columnName + '\'' +
                ", dType='" + dType + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnIndex=" + columnIndex +
                ", isHided=" + isHided +
                '}';
    }
    private DataType checkDataTypeValue(){
        DataType dataType = DataType.getDataType(this.dType);
        if(dataType == null){
            throw new IllegalArgumentException("DType이 존재하지 않습니다.");
        }
        return dataType;
    }

    private ColumnType checkColumnTypeValue(){
        ColumnType columnType = ColumnType.getColumnType(this.columnType);
        if(columnType == null){
            throw new IllegalArgumentException("Column Type이 존재하지 않습니다.");
        }
        return columnType;
    }
}

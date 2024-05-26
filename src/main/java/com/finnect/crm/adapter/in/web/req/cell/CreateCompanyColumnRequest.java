package com.finnect.crm.adapter.in.web.req.cell;

import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.column.ColumnType;
import com.finnect.crm.domain.column.DataType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCompanyColumnRequest {
    private final Long columnId;
    private final Long workspaceId;
    private final String columnName;
    private final String dType;
    private final String columnType;
    private final Double columnIndex;
    private final Boolean isHided;

    public DataColumn toDomain(){
        DataType dataType = checkDataTypeValue();
        ColumnType columnType = this.checkColumnTypeValue();

        return DataColumn.builder()
                .columnId(this.columnId)
                .workspaceId(this.workspaceId)
                .columnName(this.columnName)
                .dType(dataType)
                .columnType(columnType)
                .columnIndex(this.columnIndex)
                .isHided(this.isHided)
                .build();
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
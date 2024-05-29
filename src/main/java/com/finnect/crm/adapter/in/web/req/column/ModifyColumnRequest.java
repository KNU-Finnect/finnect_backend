package com.finnect.crm.adapter.in.web.req.column;

import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.column.ColumnType;
import com.finnect.crm.domain.column.DataType;
import lombok.Getter;


@Getter
public class ModifyColumnRequest  {
    private Long columnId;
    private String columnName;
    private String columnType;
    private Double columnIndex;
    private Boolean isHided;

    public ModifyColumnRequest(Long columnId, String columnName, String columnType,
                                   Double columnIndex, Boolean isHided) {
        this.columnId = columnId;
        this.columnName = columnName;
        this.columnType = columnType;
        this.columnIndex = columnIndex;
        this.isHided = isHided;
    }

    public DataColumn toDomain(){
        ColumnType columnType = this.checkColumnTypeValue();

        return DataColumn.builder()
                .columnId(this.columnId)
                .columnName(this.columnName)
                .columnType(columnType)
                .columnIndex(this.columnIndex)
                .isHided(this.isHided)
                .build();
    }
    @Override
    public String toString() {
        return "CreateDealColumnRequest{" +
                "columnId=" + columnId +
                ", columnName='" + columnName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnIndex=" + columnIndex +
                ", isHided=" + isHided +
                '}';
    }

    private ColumnType checkColumnTypeValue(){
        ColumnType columnType = ColumnType.getColumnType(this.columnType);
        if(columnType == null){
            throw new IllegalArgumentException("Column Type이 존재하지 않습니다.");
        }
        return columnType;
    }
}

package com.finnect.view.domain;

import com.finnect.crm.domain.column.ColumnType;
import com.finnect.view.domain.constant.SortCondition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ViewColumnDetail {
    private Long columnId;
    private ColumnType columnType;
    private String columnName;
    private Long viewId;
    private Double index;
    private Boolean hided;
    private SortCondition sorting;


    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public void setColumnName(String  columnName) {
        this.columnName = columnName;
    }
}

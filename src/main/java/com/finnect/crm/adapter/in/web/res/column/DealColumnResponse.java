package com.finnect.crm.adapter.in.web.res.column;

import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.crm.domain.column.ColumnType;
import com.finnect.crm.domain.column.DataType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DealColumnResponse {
    private Long columnId;
    private Long workspaceId;
    private String columnName;
    private String dType;
    private String columnType;
    private Double columnIndex;
    private Boolean isHided;
    @Builder
    public DealColumnResponse(Long columnId, Long workspaceId, String columnName, DataType dType, ColumnType columnType,
                              Double columnIndex, Boolean isHided) {
        this.columnId = columnId;
        this.workspaceId = workspaceId;
        this.columnName = columnName;
        this.dType = dType.getType();
        this.columnType = columnType.getType();
        this.columnIndex = columnIndex;
        this.isHided = isHided;
    }

    public static DealColumnResponse toDTO(DataColumnState columnState){
        return DealColumnResponse.builder()
                .columnId(columnState.getColumnId())
                .workspaceId(columnState.getColumnId())
                .columnName(columnState.getColumnName())
                .dType(columnState.getDType())
                .columnType(columnState.getColumnType())
                .columnIndex(columnState.getColumnIndex())
                .isHided(columnState.isHided())
                .build();
    }
}

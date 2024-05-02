package com.finnect.cell.adapter.in.web.res;

import com.finnect.cell.domain.DataColumn.ColumnType;
import com.finnect.cell.domain.DataColumn.DataType;
import com.finnect.cell.domain.state.DataColumnState;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateDealColumnResponse {
    private Long columnId;
    private Long workspaceId;
    private String columnName;
    private String dType;
    private String columnType;
    private Double columnIndex;
    private Boolean isHided;
    @Builder
    public CreateDealColumnResponse(Long columnId, Long workspaceId, String columnName, DataType dType, ColumnType columnType,
                                    Double columnIndex, Boolean isHided) {
        this.columnId = columnId;
        this.workspaceId = workspaceId;
        this.columnName = columnName;
        this.dType = dType.getType();
        this.columnType = columnType.getType();
        this.columnIndex = columnIndex;
        this.isHided = isHided;
    }

    public static CreateDealColumnResponse toDTO(DataColumnState columnState){
        return CreateDealColumnResponse.builder()
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

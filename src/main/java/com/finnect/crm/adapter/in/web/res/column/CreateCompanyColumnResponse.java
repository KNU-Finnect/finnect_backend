package com.finnect.crm.adapter.in.web.res.column;

import com.finnect.crm.domain.cell.state.DataColumnState;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CreateCompanyColumnResponse {
    private final Long columnId;
    private final Long workspaceId;
    private final String columnName;
    private final String dType;
    private final String columnType;
    private final Double columnIndex;
    private final Boolean isHided;

    public static CreateCompanyColumnResponse from(DataColumnState state){
        return CreateCompanyColumnResponse.builder()
                .columnId(state.getColumnId())
                .workspaceId(state.getColumnId())
                .columnName(state.getColumnName())
                .dType(state.getDType().getType())
                .columnType(state.getColumnType().getType())
                .columnIndex(state.getColumnIndex())
                .isHided(state.isHided())
                .build();
    }
}

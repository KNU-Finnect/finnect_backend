package com.finnect.cell.domain;

import com.finnect.cell.domain.state.ColumnState;
import lombok.Builder;

public class Column implements ColumnState {
    private Long columnId;
    private Long workspaceId;

    @Builder
    public Column(Long columnId, Long workspaceId) {
        this.columnId = columnId;
        this.workspaceId = workspaceId;
    }

    @Override
    public Long getColumnId() {
        return this.columnId;
    }

    @Override
    public Long getWorkspaceId() {
        return this.workspaceId;
    }

}

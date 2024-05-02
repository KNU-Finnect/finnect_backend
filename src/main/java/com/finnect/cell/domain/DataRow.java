package com.finnect.cell.domain;

import com.finnect.cell.domain.state.DataRowState;
import lombok.Builder;

public class DataRow implements DataRowState {
    private Long dataRowId;

    @Builder
    public DataRow(Long dataRowId) {
        this.dataRowId = dataRowId;
    }

    @Override
    public Long getDataRowId() {
        return this.dataRowId;
    }
}

package com.finnect.crm.domain.cell;

import com.finnect.crm.domain.cell.state.DataRowState;
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

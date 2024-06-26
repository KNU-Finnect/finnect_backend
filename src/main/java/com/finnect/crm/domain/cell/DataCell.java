package com.finnect.crm.domain.cell;

import com.finnect.crm.adapter.out.persistence.cell.CellId;
import com.finnect.crm.domain.cell.state.DataCellState;
import lombok.Builder;

public class DataCell implements DataCellState {
    private CellId cellId;
    private String value;
    private Long userId;
    private Long peopleId;
    private Long companyId;

    @Builder
    public DataCell(CellId cellId, String value, Long userId, Long peopleId, Long companyId) {
        this.cellId = cellId;
        this.value = value;
        this.userId = userId;
        this.peopleId = peopleId;
        this.companyId = companyId;
    }

    @Override
    public Long getColumnId() {
        return this.cellId.getColumnId();
    }

    @Override
    public Long getRowId() {
        return this.cellId.getDataRowId();
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public Long getUserId() {
        return this.userId;
    }

    @Override
    public Long getPeopleId() {
        return this.peopleId;
    }

    @Override
    public Long getCompanyId() {
        return this.companyId;
    }

    public void setValue(String value){
        this.value = value;
    }

    public void resetValue(){
        this.setValue(null);
    }

    @Override
    public String toString() {
        return "DataCell{" +
                "cellId=" + cellId +
                ", value='" + value + '\'' +
                ", userId=" + userId +
                ", peopleId=" + peopleId +
                ", companyId=" + companyId +
                '}';
    }
}

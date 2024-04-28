package com.finnect.cell.adapter.out.persistence;

import com.finnect.cell.domain.state.DataCellState;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Builder;

@Entity(name = "data_cell")
public class DataCellEntity implements DataCellState {

    @EmbeddedId
    private CellId cellId;

    private String value;
    private Long userId;
    private Long peopleId;
    private Long companyId;
    @Builder
    public DataCellEntity(Long rowId, Long columnId, String value, Long userId, Long peopleId, Long companyId) {
        this.cellId = new CellId(rowId, columnId);
        this.value = value;
        this.userId = userId;
        this.peopleId = peopleId;
        this.companyId = companyId;
    }
    protected DataCellEntity() {}

    @Override
    public Long getColumnId() {
        return this.cellId.getColumnId();
    }

    @Override
    public Long getRowId() {
        return this.cellId.getRowId();
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
}

package com.finnect.cell.adapter.out.persistence;

import com.finnect.cell.domain.state.CellState;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity(name = "cell")
public class CellEntity implements CellState {

    @EmbeddedId
    private CellId cellId;

    private String value;
    private Long userId;
    private Long peopleId;
    private Long companyId;
    @Builder
    public CellEntity(Long rowId, Long columnId, String value, Long userId, Long peopleId, Long companyId) {
        this.cellId = new CellId(rowId, columnId);
        this.value = value;
        this.userId = userId;
        this.peopleId = peopleId;
        this.companyId = companyId;
    }
    protected CellEntity() {}

    @Override
    public Long getColumn() {
        return this.cellId.getColumnId();
    }

    @Override
    public Long getRow() {
        return this.cellId.getRowId();
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public Long getUser() {
        return this.userId;
    }

    @Override
    public Long getPeople() {
        return this.peopleId;
    }

    @Override
    public Long getCompany() {
        return this.companyId;
    }
}

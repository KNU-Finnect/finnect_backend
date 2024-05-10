package com.finnect.crm.adapter.out.cell.persistence;

import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.cell.state.DataCellState;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Builder;

@Entity(name = "data_cell")
public class DataCellEntity implements DataCellState {

    @EmbeddedId
    private CellId cellId;

    @ManyToOne
    @MapsId("dataRowId")
    @JoinColumn(name = "data_row_id")
    private DataRowEntity dataRow;

    private String value;
    private Long userId;
    private Long peopleId;
    private Long companyId;
    @Builder
    public DataCellEntity(DataRowEntity row, Long columnId, String value, Long userId, Long peopleId, Long companyId) {
        this.cellId = new CellId(row.getDataRowId(), columnId);
        this.dataRow = row;
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

    public DataCell toDomain(){
        return DataCell.builder()
                .cellId(new CellId(this.getRowId(), this.getColumnId()))
                .value(this.value)
                .companyId(this.companyId)
                .peopleId(this.peopleId)
                .userId(this.userId)
                .build();
    }
}

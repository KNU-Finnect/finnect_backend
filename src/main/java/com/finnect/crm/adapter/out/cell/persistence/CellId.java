package com.finnect.crm.adapter.out.cell.persistence;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Getter;

@Getter
@Embeddable
public class CellId implements Serializable {

    private Long dataRowId;
    private Long columnId;

    public CellId(Long dataRowId, Long columnId) {
        this.dataRowId = dataRowId;
        this.columnId = columnId;
    }

    public CellId() {
    }

    @Override
    public String toString() {
        return "CellId{" +
                "dataRowId=" + dataRowId +
                ", columnId=" + columnId +
                '}';
    }
}

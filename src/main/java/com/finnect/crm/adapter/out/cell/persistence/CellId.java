package com.finnect.crm.adapter.out.cell.persistence;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import java.io.Serializable;
import lombok.Getter;

@Getter
@Embeddable
public class CellId implements Serializable {

    private Long rowId;
    private Long columnId;

    public CellId(Long rowId, Long columnId) {
        this.rowId = rowId;
        this.columnId = columnId;
    }

    public CellId() {

    }
}

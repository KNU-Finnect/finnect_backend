package com.finnect.crm.adapter.out.cell.persistence;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import java.io.Serializable;
import lombok.Getter;

@Getter
@Embeddable
public class CellIdEntity implements Serializable {

    private Long dataRowId;
    private Long columnId;

    public CellIdEntity(Long dataRowId, Long columnId) {
        this.dataRowId = dataRowId;
        this.columnId = columnId;
    }

    public CellIdEntity() {
    }
}

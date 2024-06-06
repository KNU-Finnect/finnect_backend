package com.finnect.crm.adapter.in.web.req.cell;

import com.finnect.crm.adapter.out.persistence.cell.CellId;
import com.finnect.crm.domain.cell.DataCell;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyCellRequest {
    @NotNull
    private Long columnId;
    @NotNull
    private Long rowId;
    @NotNull
    private String value;
    private Long userId;
    private Long peopleId;
    private Long companyId;

    public DataCell toDomain(){
        return DataCell.builder()
                .cellId(new CellId(rowId, columnId))
                .value(this.value)
                .userId(userId)
                .peopleId(peopleId)
                .companyId(companyId)
            .build();
    }
}

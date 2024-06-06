package com.finnect.crm.adapter.in.web.res.company;

import com.finnect.crm.domain.cell.state.DataCellState;
import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.crm.domain.company.CompanyDetail;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class CellDto {

    private final Long columnId;
    private final String value;

    private final Long userId;
    private final Long peopleId;

    private final String columnType;
    private final String columnName;

    public static CellDto from(CompanyDetail.Cell cell) {
        return CellDto.builder()
                .columnId(cell.getColumnId())
                .value(cell.getValue())
                .userId(cell.getUserId())
                .peopleId(cell.getPeopleId())
                .columnName(cell.getColumnName())
                .columnType(cell.getColumnType())
                .build();
    }
}

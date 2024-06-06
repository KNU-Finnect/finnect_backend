package com.finnect.crm.domain.company;

import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.cell.state.DataCellState;
import com.finnect.crm.domain.column.ColumnType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class CompanyDetail {

    private Company company;
    private List<Cell> cells;

    public static CompanyDetail of(CompanyState companyState, List<DataCell> cells, Map<Long, String> columnNameMap, Map<Long, ColumnType> columnTypeMap) {
        List<CompanyDetail.Cell> companyCells = cells.stream()
                .map(dataCell -> CompanyDetail.Cell.of(
                        dataCell,
                        columnNameMap.get(dataCell.getColumnId()),
                        columnTypeMap.get(dataCell.getColumnId()).getType()
                ))
                .toList();

        return CompanyDetail.builder()
                .company(Company.from(companyState))
                .cells(companyCells)
                .build();
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    public static class Cell {
        private Long columnId;

        private String value;
        private Long userId;
        private Long peopleId;

        private String columnName;
        private String columnType;

        public static Cell of(DataCellState cellState, String columnName, String columnType) {
            return Cell.builder()
                    .columnId(cellState.getColumnId())
                    .value(cellState.getValue())
                    .userId(cellState.getUserId())
                    .peopleId(cellState.getPeopleId())
                    .columnName(columnName)
                    .columnType(columnType)
                    .build();
        }
    }
}

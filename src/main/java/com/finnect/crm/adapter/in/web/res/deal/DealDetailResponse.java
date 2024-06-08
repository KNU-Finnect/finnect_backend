package com.finnect.crm.adapter.in.web.res.deal;

import com.finnect.crm.domain.cell.state.DataCellState;
import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.crm.domain.column.ColumnType;
import com.finnect.crm.domain.deal.DealCellDetail;
import com.finnect.crm.domain.deal.state.DealState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DealDetailResponse {
    private Long dealId;
    private String dealName;
    private Long companyId;
    private String companyName;
    private Long userId;
    private String userName;
    private List<CellInfo> cells;

    @Builder
    public DealDetailResponse(DealCellDetail dealCellDetail) {
        this.dealId = dealCellDetail.getDealId();
        this.dealName = dealCellDetail.getDealName();
        this.companyId = dealCellDetail.getCompanyId();
        this.companyName = dealCellDetail.getCompanyName();
        this.userId = dealCellDetail.getUserId();
        this.userName = dealCellDetail.getUserName();

        this.cells = getCells(dealCellDetail);
    }
    private List<CellInfo> getCells(DealCellDetail dealCellDetail){
        Map<Long, DataCellState> cellStateMap = new HashMap<>();
        for (DataCellState cellState : dealCellDetail.getDataCells()) {
            cellStateMap.put(cellState.getColumnId(), cellState);
        }
        List<CellInfo> cellInfos = new ArrayList<>();
        for (DataColumnState columnStateItem : dealCellDetail.getDataColumns()) {
            DataCellState matchedCellState = cellStateMap.get(columnStateItem.getColumnId());
            if (matchedCellState != null) {
                cellInfos.add(new CellInfo(columnStateItem, matchedCellState));
                continue;
            }
            throw new IllegalArgumentException("CellType과 ColumnType이 일치하지 않습니다.");
        }
        return cellInfos;
    }

    @Getter
    class CellInfo{
        private ColumnType columnType;
        private Long rowId;
        private Long columnId;
        private String value;
        private Long userId;
        private Long companyId;
        private Long peopleId;

        public CellInfo(DataColumnState dataColumn, DataCellState dataCell) {
            this.rowId = dataCell.getRowId();
            this.columnId = dataCell.getColumnId();
            this.value = dataCell.getValue();
            this.columnType = dataColumn.getColumnType();
            this.userId = dataCell.getUserId();
            this.companyId = dataCell.getCompanyId();
            this.peopleId = dataCell.getCompanyId();
        }
    }
}

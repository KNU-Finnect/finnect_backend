package com.finnect.crm.adapter.in.web.res.deal;

import com.finnect.crm.domain.cell.DataColumn.ColumnType;
import com.finnect.crm.domain.cell.state.DataCellState;
import com.finnect.crm.domain.cell.state.DataColumnState;
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
    private Long workspaceId;
    private List<CellInfo> cells;
    private Responsability responsability;

    @Builder
    public DealDetailResponse(DealState dealState, List<DataColumnState> columnState, List<DataCellState> cellStates) {
        this.dealId = dealState.getDealId();
        this.workspaceId = dealState.getWorkspaceId();
        this.cells = getCells(columnState, cellStates);
        this.responsability = null;
    }
    private List<CellInfo> getCells(List<DataColumnState> columnState, List<DataCellState> cellStates){
        Map<Long, DataCellState> cellStateMap = new HashMap<>();
        for (DataCellState cellState : cellStates) {
            cellStateMap.put(cellState.getColumnId(), cellState);
        }
        List<CellInfo> cellInfos = new ArrayList<>();
        for (DataColumnState columnStateItem : columnState) {
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
        private Long rowId;
        private Long columnId;
        private String value;
        private ColumnType columnType;
        private Long userId;

        public CellInfo(DataColumnState dataColumn, DataCellState dataCell) {
            this.rowId = dataCell.getRowId();
            this.columnId = dataCell.getColumnId();
            this.value = dataCell.getValue();
            this.columnType = dataColumn.getColumnType();
            this.userId = dataCell.getUserId();
        }
    }
    class Responsability{
        private Long userId;
        private String nickName;
        private String role;

    }
}

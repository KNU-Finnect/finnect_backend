package com.finnect.view.adapter.in.web.res;

import com.finnect.crm.domain.deal.DealCell;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DealInfoResponse {
    private Long dealId;
    private String dealName;
    private Long companyId;
    private Long userId;
    private Long rowId;
    private List<CellInfoResponse> cells;

    @Builder
    public DealInfoResponse(DealCell dealCell) {
        this.dealId = dealCell.getDealId();
        this.dealName = dealCell.getDealName();
        this.companyId = dealCell.getCompanyId();
        this.userId = null;
        this.rowId = dealCell.getDataCellStates().get(0).getRowId();
        this.cells = dealCell.getDataCellStates()
                .stream()
                .map(dataCellState ->
                            CellInfoResponse.builder()
                                    .columnId(dataCellState.getColumnId())
                                    .value(dataCellState.getValue())
                                    .companyId(dataCellState.getCompanyId())
                                    .userId(dataCellState.getUserId())
                                    .peopleId(dataCellState.getPeopleId())
                            .build()
                ).toList();
    }
}

package com.finnect.view.adapter.in.web.res;

import com.finnect.crm.domain.company.CompanyCell;
import com.finnect.crm.domain.deal.DealCell;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CompanyInfoResponse {
    private Long companyId;
    private String domain;
    private String companyName;
    private Long rowId;
    private List<CellInfoResponse> cells;

    @Builder
    public CompanyInfoResponse(CompanyCell companyCell) {

        this.companyId = companyCell.getCompanyId();
        this.domain = companyCell.getCompanyDomain();
        this.companyName = companyCell.getCompanyName();
        this.rowId = companyCell.getDataCellStates().get(0).getRowId();
        this.cells = companyCell.getDataCellStates()
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

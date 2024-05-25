package com.finnect.crm.domain.deal;

import com.finnect.crm.domain.cell.state.DataCellState;
import com.finnect.crm.domain.deal.state.DealCellState;
import java.util.ArrayList;
import java.util.List;
import lombok.ToString;

@ToString
public class DealCell implements DealCellState {
    private Long dealId;
    private Long companyId;
    private String dealName;
    private List<DataCellState> dataCellStates;

    public DealCell(Long dealId, Long companyId, String dealName) {
        this.dealId = dealId;
        this.companyId = companyId;
        this.dealName = dealName;
        this.dataCellStates = new ArrayList<>();
    }

    @Override
    public Long getDealId() {
        return this.dealId;
    }

    @Override
    public Long getCompanyId() {
        return companyId;
    }

    @Override
    public String getDealName() {
        return this.dealName;
    }

    @Override
    public List<DataCellState> getDataCellStates() {
        return dataCellStates;
    }

    public void addCell(DataCellState cell){
        this.dataCellStates.add(cell);
    }

}

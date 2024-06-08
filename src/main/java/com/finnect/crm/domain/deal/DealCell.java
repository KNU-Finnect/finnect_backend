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
    private String companyName;
    private Long userId;
    private String userName;
    private String dealName;
    private List<DataCellState> dataCellStates;

    public DealCell(Long dealId, Long companyId, String companyName, String dealName, Long userId) {
        this.dealId = dealId;
        this.companyId = companyId;
        this.companyName = companyName;
        this.dealName = dealName;
        this.dataCellStates = new ArrayList<>();
        this.userId = userId;
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
    public String getCompanyName() {
        return this.companyName;
    }

    @Override
    public Long getUserId() {
        return this.userId;
    }

    @Override
    public String getUserName() {
        return this.userName;
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

    public void setUserName(String userName){
        this.userName = userName;
    }

}

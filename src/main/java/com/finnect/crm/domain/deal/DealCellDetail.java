package com.finnect.crm.domain.deal;

import com.finnect.crm.domain.cell.state.DataCellState;
import com.finnect.crm.domain.column.state.DataColumnState;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DealCellDetail {
    private Long dealId;
    private String dealName;
    private Long companyId;
    private String companyName;
    private Long userId;
    private String userName;

    private List<DataColumnState> dataColumns;
    private List<DataCellState> dataCells;

    @Builder
    public DealCellDetail(Long dealId, String dealName, Long companyId, String companyName, Long userId,
                          String userName) {
        this.dealId = dealId;
        this.dealName = dealName;
        this.companyId = companyId;
        this.companyName = companyName;
        this.userId = userId;
        this.userName = userName;
        this.dataColumns = new ArrayList<>();
        this.dataCells = new ArrayList<>();
    }

    public void appendCell(DataCellState dataCellState){
        this.dataCells.add(dataCellState);
    }

    public void appendColumn(DataColumnState dataCellState){
        this.dataColumns.add(dataCellState);
    }


    @Override
    public String toString() {
        return "DealCellDetail{" +
                "dealId=" + dealId +
                ", dealName='" + dealName + '\n' +
                ", companyId=" + companyId + '\n' +
                ", companyName='" + companyName + '\n' +
                ", userId=" + userId + '\n' +
                ", userName='" + userName + '\n' +
                ", dataColumns=" + dataColumns + '\n' +
                ", dataCells=" + dataCells +
                '}';
    }
}

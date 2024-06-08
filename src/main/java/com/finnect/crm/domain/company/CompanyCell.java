package com.finnect.crm.domain.company;

import com.finnect.crm.domain.cell.state.DataCellState;
import com.finnect.crm.domain.company.state.CompanyCellState;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CompanyCell implements CompanyCellState {

    private Long companyId;
    private String companyName;
    private String companyDomain;
    private List<DataCellState> dataCellStates;

    @Builder
    public CompanyCell(Long companyId, String companyName, String companyDomain) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyDomain = companyDomain;
        this.dataCellStates = new ArrayList<>();
    }

    public void addCell(DataCellState cell){
        this.dataCellStates.add(cell);
    }
}

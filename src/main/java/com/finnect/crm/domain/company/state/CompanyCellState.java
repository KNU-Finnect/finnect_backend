package com.finnect.crm.domain.company.state;

import com.finnect.crm.domain.cell.state.DataCellState;
import java.util.List;

public interface CompanyCellState {

    Long getCompanyId();
    String getCompanyName();
    String getCompanyDomain();
    List<DataCellState> getDataCellStates();
}

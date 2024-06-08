package com.finnect.crm.domain.deal.state;

import com.finnect.crm.domain.cell.state.DataCellState;
import java.util.List;

public interface DealCellState {

    Long getDealId();
    Long getCompanyId();
    String getCompanyName();
    Long getUserId();
    String getUserName();
    String getDealName();
    List<DataCellState> getDataCellStates();
}

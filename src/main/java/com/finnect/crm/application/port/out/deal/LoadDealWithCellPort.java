package com.finnect.crm.application.port.out.deal;

import com.finnect.crm.domain.deal.DealCell;
import com.finnect.view.domain.state.FilterState;
import java.util.List;

public interface LoadDealWithCellPort {

    List<DealCell> loadDealsWithCellsByFilter(List<FilterState> filters, final int startPage, final int columnCount);
}

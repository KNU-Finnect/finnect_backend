package com.finnect.crm.application.port.in.deal;

import com.finnect.crm.domain.deal.DealCell;
import com.finnect.crm.domain.deal.DealCellDetail;
import com.finnect.view.domain.state.FilterState;
import java.util.List;

public interface LoadDealWithCellUseCase {

    List<DealCell> loadDealWithCell(Long workspaceId, List<FilterState> filters, Integer page);

    DealCellDetail loadDealWithCellDetail(Long workspaceId, Long dealId);
}

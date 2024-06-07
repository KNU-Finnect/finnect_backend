package com.finnect.crm.application.port.in.company;

import com.finnect.crm.domain.company.CompanyCell;
import com.finnect.crm.domain.deal.DealCell;
import com.finnect.view.domain.state.FilterState;
import java.util.List;

public interface LoadCompanyWithCellUseCase {


    List<CompanyCell> loadCompanyWithCell(Long workspaceId, List<FilterState> filters, Integer page);
}

package com.finnect.crm.application.port.out.company;

import com.finnect.crm.domain.company.CompanyCell;
import com.finnect.view.domain.state.FilterState;
import java.util.List;

public interface LoadCompanyWithCellPort {

    List<CompanyCell> loadCompaniesWithCellsByFilter(List<FilterState> filters, final int startPage, final int columnCount);
}

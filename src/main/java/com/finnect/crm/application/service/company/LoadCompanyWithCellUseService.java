package com.finnect.crm.application.service.company;

import com.finnect.crm.application.port.in.company.LoadCompanyWithCellUseCase;
import com.finnect.crm.application.port.out.column.LoadColumnCountPort;
import com.finnect.crm.application.port.out.company.LoadCompanyWithCellPort;
import com.finnect.crm.domain.company.CompanyCell;
import com.finnect.view.domain.state.FilterState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoadCompanyWithCellUseService implements LoadCompanyWithCellUseCase {
    private final LoadCompanyWithCellPort loadCompanyWithCellPort;
    private final LoadColumnCountPort loadColumnCountPort;
    @Override
    public List<CompanyCell> loadCompanyWithCell(Long workspaceId, List<FilterState> filters, Integer page) {
        // Column 개수 반환
        var columnCnt = loadColumnCountPort.loadCompanyColumnCount(workspaceId);

        var companyCells = loadCompanyWithCellPort.loadCompaniesWithCellsByFilter(filters, page, columnCnt);
        log.info(companyCells.toString());
        return companyCells;
    }
}

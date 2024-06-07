package com.finnect.crm.application.service.deal;

import com.finnect.crm.application.port.in.cell.LoadDealWithCellUseCase;
import com.finnect.crm.application.port.out.column.LoadColumnCountPort;
import com.finnect.crm.application.port.out.deal.LoadDealWithCellPort;
import com.finnect.crm.domain.deal.DealCell;
import com.finnect.view.domain.state.FilterState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoadDealWithCellService implements LoadDealWithCellUseCase {
    private final LoadDealWithCellPort loadDealWithCellPort;
    private final LoadColumnCountPort loadColumnCountPort;
    @Override
    public List<DealCell> loadDealWithCell(Long workspaceId, List<FilterState> filters, Integer page) {
        int columnCount = loadColumnCountPort.loadDealColumnCount(workspaceId);
        log.info("ColumnCount : " + columnCount);
        List<DealCell> dealCells = loadDealWithCellPort.loadDealsWithCellsByFilter(filters, page, columnCount);
        log.info(dealCells.toString());
        return dealCells;
    }
}

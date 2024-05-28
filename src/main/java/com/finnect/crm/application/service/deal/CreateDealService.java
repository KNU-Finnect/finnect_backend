package com.finnect.crm.application.service.deal;

import com.finnect.crm.application.port.in.cell.CreateNewRowUseCase;
import com.finnect.crm.application.port.in.dealLog.CreateDealLogUseCase;
import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.cell.state.DataRowState;
import com.finnect.crm.application.port.in.deal.CreateDealUseCase;
import com.finnect.crm.application.port.out.deal.SaveDealPort;
import com.finnect.crm.domain.deal.Deal;
import com.finnect.crm.domain.deal.state.DealState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateDealService implements CreateDealUseCase {

    private final SaveDealPort saveDealPort;
    private final CreateNewRowUseCase createNewRowUseCase;
    private final CreateDealLogUseCase createDealLogUseCase;
    @Override
    public DealState createDeal(Deal deal) {

        DataRowState dataRow = createNewRowUseCase.createNewDataRow(
                DataColumn.builder()
                        .workspaceId(deal.getWorkspaceId())
                        .build()
        );

        deal.setDataRowId(dataRow.getDataRowId());
        deal = saveDealPort.saveDeal(deal);
        log.info(deal.toString());
        createDealLogUseCase.createLogWithNewDeal(deal.getDealId());
        return deal;
    }
}

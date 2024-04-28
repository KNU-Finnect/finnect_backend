package com.finnect.deal.application.service;

import com.finnect.cell.application.port.in.CreateNewRowUseCase;
import com.finnect.cell.domain.Column;
import com.finnect.cell.domain.state.DataRowState;
import com.finnect.deal.application.DealState;
import com.finnect.deal.application.port.in.CreateDealUseCase;
import com.finnect.deal.application.port.out.SaveDealPort;
import com.finnect.deal.domain.Deal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateDealService implements CreateDealUseCase {

    private final SaveDealPort saveDealPort;
    private final CreateNewRowUseCase createNewRowUseCase;
    @Override
    public DealState createDeal(Deal deal) {

        DataRowState dataRow = createNewRowUseCase.createNewDataRow(
                Column.builder()
                        .workspaceId(deal.getWorkspaceId())
                        .build()
        );

        deal.setDataRowId(dataRow.getDataRowId());
        deal = saveDealPort.saveDeal(deal);
        log.info(deal.toString());
        return deal;
    }
}

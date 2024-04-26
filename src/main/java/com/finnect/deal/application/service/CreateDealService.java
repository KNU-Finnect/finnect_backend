package com.finnect.deal.application.service;

import com.finnect.cell.application.port.in.SaveCellUseCase;
import com.finnect.cell.domain.DataRow;
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
    private final SaveCellUseCase saveCellUseCase;
    @Override
    public DealState createDeal(Deal deal) {
        DataRow dataRow = saveCellUseCase.createNewDatarow();
        deal.setDataRowId(dataRow.getDataRowId());
        log.info(deal.toString());
        saveDealPort.saveDeal(deal);

        return null;
    }
}

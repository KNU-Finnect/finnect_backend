package com.finnect.crm.application.service.dealLog;

import com.finnect.crm.application.port.in.dealLog.CreateDealLogUseCase;
import com.finnect.crm.application.port.out.deal.LoadDealPort;
import com.finnect.crm.application.port.out.dealLog.SaveDealLogPort;
import com.finnect.crm.domain.dealLog.DealLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDealLogService implements CreateDealLogUseCase {
    private final LoadDealPort loadDealPort;
    private final SaveDealLogPort saveDealLogPort;
    @Override
    public void createLogWithNewDeal(Long dealId) {
        DealLog dealLog = DealLog.createLogWithNewDeal(dealId);
        saveDealLogPort.saveDealLog(dealLog);
    }

    @Override
    public void createLogWithAttributeChange(Long rowId, String before, String after) {
        var deal = loadDealPort.findDealByRowId(rowId);
        DealLog dealLog = DealLog.createLogWithAttributeChange(deal.getDealId(), before, after);
        saveDealLogPort.saveDealLog(dealLog);
    }
}

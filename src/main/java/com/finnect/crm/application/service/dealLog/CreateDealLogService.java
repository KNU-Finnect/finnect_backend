package com.finnect.crm.application.service.dealLog;

import com.finnect.crm.application.port.in.dealLog.CreateDealLogUseCase;
import com.finnect.crm.application.port.out.dealLog.SaveDealLogPort;
import com.finnect.crm.domain.dealLog.DealLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDealLogService implements CreateDealLogUseCase {
    private final SaveDealLogPort saveDealLogPort;
    @Override
    public void createLogWithNewDeal(Long dealId) {
        DealLog dealLog = DealLog.createLogWithNewDeal(dealId);
        saveDealLogPort.saveDealLog(dealLog);
    }

    @Override
    public void createLogWithAttributeChange(Long dealId, String before, String after) {

    }
}

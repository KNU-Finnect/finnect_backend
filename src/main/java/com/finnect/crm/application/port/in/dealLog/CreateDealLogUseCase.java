package com.finnect.crm.application.port.in.dealLog;

public interface CreateDealLogUseCase {

    void createLogWithNewDeal(Long dealId);
    void createLogWithAttributeChange(Long dealId, String before, String after);
}

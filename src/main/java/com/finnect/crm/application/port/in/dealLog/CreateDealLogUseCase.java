package com.finnect.crm.application.port.in.dealLog;

public interface CreateDealLogUseCase {

    void createLogWithNewDeal(Long dealId);
    void createLogWithAttributeChange(Long rowId, String before, String after);
}

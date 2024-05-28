package com.finnect.crm.application.port.out.dealLog;

import com.finnect.crm.domain.dealLog.state.DealLogState;

public interface SaveDealLogPort {
    void saveDealLog(DealLogState dealLog);
}

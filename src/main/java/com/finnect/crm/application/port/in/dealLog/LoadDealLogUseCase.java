package com.finnect.crm.application.port.in.dealLog;

import com.finnect.crm.domain.dealLog.state.DealLogState;
import java.util.List;

public interface LoadDealLogUseCase {

    List<DealLogState> loadAllDealLog(Long dealId);
}

package com.finnect.crm.application.port.out.dealLog;

import com.finnect.crm.domain.dealLog.DealLog;
import java.util.List;

public interface LoadDealLogPort {
    List<DealLog> loadDealLogs(Long dealId);
}

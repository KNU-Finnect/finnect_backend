package com.finnect.crm.domain.dealLog.state;

import java.time.LocalDateTime;

public interface DealLogState {

    Long getDealId();
    Long getDealLogId();
    String getLog();
    LocalDateTime getSavedTime();
}

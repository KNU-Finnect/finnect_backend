package com.finnect.crm.adapter.in.web.res.dealLog;

import com.finnect.crm.domain.dealLog.state.DealLogState;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DealLogResponse {
    private Long dealLogId;
    private Long dealId;
    private String dealLog;
    private LocalDateTime savedTime;


    public DealLogResponse(DealLogState dealLogState) {
        this.dealLogId = dealLogState.getDealLogId();
        this.dealId = dealLogState.getDealId();
        this.dealLog = dealLogState.getLog();
        this.savedTime = dealLogState.getSavedTime();
    }
}

package com.finnect.crm.domain.dealLog;

import com.finnect.crm.domain.dealLog.state.DealLogState;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Builder;

public class DealLog implements DealLogState {
    private Long dealLogId;
    private Long dealId;
    private String dealLog;
    private LocalDateTime savedTime;
    private static final ZoneId timeZone = ZoneId.of("Asia/Seoul");
    @Builder
    private DealLog(Long dealLogId, Long dealId, String dealLog, LocalDateTime savedTime) {
        this.dealLogId = dealLogId;
        this.dealId = dealId;
        this.dealLog = dealLog;
        this.savedTime = savedTime;
    }


    public static DealLog createLogWithNewDeal(Long dealId){
        return DealLog.builder()
                .dealId(dealId)
                .dealLog("DEAL이 생성되었습니다.")
                .savedTime(LocalDateTime.now(timeZone))
                .build();
    }

    public static DealLog createLogWithAttributeChange (Long dealId, String before, String after){

        return DealLog.builder()
                .dealId(dealId)
                .dealLog("DEAL 속성이 수정되었습니다." + before + " -> " + after)
                .savedTime(LocalDateTime.now(timeZone))
                .build();
    }

    @Override
    public Long getDealId() {
        return this.dealId;
    }

    @Override
    public Long getDealLogId() {
        return this.dealLogId;
    }

    @Override
    public String getLog() {
        return this.dealLog;
    }

    @Override
    public LocalDateTime getSavedTime() {
        return this.savedTime;
    }
}

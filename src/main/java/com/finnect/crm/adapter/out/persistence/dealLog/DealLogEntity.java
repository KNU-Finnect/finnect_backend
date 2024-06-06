package com.finnect.crm.adapter.out.persistence.dealLog;

import com.finnect.crm.domain.dealLog.DealLog;
import com.finnect.crm.domain.dealLog.state.DealLogState;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.ToString;

@Entity(name = "deal_log")
@ToString
public class DealLogEntity implements DealLogState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealLogId;
    private Long dealId;
    private String dealLog;
    private LocalDateTime savedTime;

    public DealLogEntity() {
    }

    @Builder
    private DealLogEntity(Long dealLogId, Long dealId, String dealLog, LocalDateTime savedTime) {
        this.dealLogId = dealLogId;
        this.dealId = dealId;
        this.dealLog = dealLog;
        this.savedTime = savedTime;
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

    public static DealLogEntity toEntity(DealLogState dealLogState){
        return DealLogEntity.builder()
                .dealId(dealLogState.getDealId())
                .dealLog(dealLogState.getLog())
                .savedTime(dealLogState.getSavedTime())
            .build();
    }

    public DealLog toDomain(){
        return DealLog.builder()
                .dealLogId(this.dealLogId)
                .dealId(this.dealId)
                .dealLog(dealLog)
                .savedTime(savedTime)
                .build();
    }
}

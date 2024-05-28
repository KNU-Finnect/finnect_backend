package com.finnect.crm.adapter.out.persistence.dealLog;

import com.finnect.crm.application.port.out.dealLog.SaveDealLogPort;
import com.finnect.crm.domain.dealLog.DealLog;
import com.finnect.crm.domain.dealLog.state.DealLogState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DealLogAdapter implements SaveDealLogPort {
    private final DealLogRepository dealLogRepository;
    @Override
    public void saveDealLog(DealLogState dealLog) {
        DealLogEntity dealLogEntity = DealLogEntity.toEntity(dealLog);
        dealLogRepository.save(dealLogEntity);
        log.info(dealLogEntity.toString());
    }
}

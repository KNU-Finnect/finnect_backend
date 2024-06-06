package com.finnect.crm.application.service.dealLog;

import com.finnect.crm.application.port.in.dealLog.LoadDealLogUseCase;
import com.finnect.crm.application.port.out.dealLog.LoadDealLogPort;
import com.finnect.crm.domain.dealLog.DealLog;
import com.finnect.crm.domain.dealLog.state.DealLogState;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoadDealLogService implements LoadDealLogUseCase {
    private final LoadDealLogPort loadDealLogPort;
    @Override
    public List<DealLogState> loadAllDealLog(Long dealId) {
        List<DealLog> dealLogs = loadDealLogPort.loadDealLogs(dealId);
        log.info(dealLogs.toString());
        return new ArrayList<>(dealLogs);
    }
}

package com.finnect.crm.adapter.in.web.controller.dealLog;


import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.crm.adapter.in.web.res.dealLog.DealLogResponse;
import com.finnect.crm.application.port.in.dealLog.LoadDealLogUseCase;
import com.finnect.crm.domain.dealLog.state.DealLogState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DealLogController {
    private final LoadDealLogUseCase loadDealLogUseCase;

    @GetMapping("/workspaces/deals/{dealId}/logs")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResult<List<DealLogResponse>>> loadDealLogs(@PathVariable(value = "dealId") Long dealId){
        List<DealLogState> dealLogs = loadDealLogUseCase.loadAllDealLog(dealId);

        return ResponseEntity.ok(ApiUtils.success(
                HttpStatus.OK,
                dealLogs.stream()
                        .map(DealLogResponse::new)
                        .toList()
                )
        );
    }
}

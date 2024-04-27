package com.finnect.deal.adapter.in.web;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.deal.adapter.in.web.req.CreateDealRequest;
import com.finnect.deal.adapter.in.web.res.CreateDealResponse;
import com.finnect.deal.application.DealState;
import com.finnect.deal.application.port.in.CreateDealUseCase;
import com.finnect.deal.domain.Deal;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DealController {

    private final CreateDealUseCase createDealUseCase;

    @PostMapping("/workspaces/deals")
    public ResponseEntity<ApiResult<CreateDealResponse>> createDeal(
            @RequestBody CreateDealRequest createDealRequest){

        log.info(createDealRequest.toString());
        DealState dealState = createDealUseCase
                .createDeal(
                    Deal.builder()
                            .workspaceId(createDealRequest.getWorkspaceId())
                            .companyId(createDealRequest.getCompanyId())
                            .dealName(createDealRequest.getDealName())
                            .userId(createDealRequest.getUserId())
                            .build()
        );

        CreateDealResponse dto = CreateDealResponse.toDTO(dealState);
        log.info(dto.toString());
        return ResponseEntity.
                ok(ApiUtils.success(HttpStatus.CREATED, dto));
    }
}

package com.finnect.crm.adapter.in.web.controller.deal;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.crm.adapter.in.web.req.deal.CreateDealRequest;
import com.finnect.crm.adapter.in.web.res.deal.CreateDealResponse;
import com.finnect.crm.adapter.in.web.res.deal.DealDetailResponse;
import com.finnect.crm.application.port.in.deal.CreateDealUseCase;
import com.finnect.crm.application.port.in.deal.LoadDealWithCellUseCase;
import com.finnect.crm.domain.deal.DealCellDetail;
import com.finnect.crm.domain.deal.state.DealState;
import com.finnect.common.vo.WorkspaceAuthority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DealController {

    private final CreateDealUseCase createDealUseCase;
    private final LoadDealWithCellUseCase loadDealWithCellUseCase;

    @PostMapping("/workspaces/deals")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResult<CreateDealResponse>> createDeal(
            @RequestBody CreateDealRequest createDealRequest){

        log.info(createDealRequest.toString());
        DealState dealState = createDealUseCase.createDeal(
                   createDealRequest.toDomain(
                           WorkspaceAuthority.from(SecurityContextHolder
                                   .getContext()
                                   .getAuthentication()
                                   .getAuthorities()).workspaceId().value(),
                           Long.valueOf((String) SecurityContextHolder
                                   .getContext()
                                   .getAuthentication()
                                   .getDetails()
                           )
                   )
        );

        return new ResponseEntity<>
                (ApiUtils.success(HttpStatus.CREATED, CreateDealResponse.toDTO(dealState))
                        , HttpStatus.CREATED);
    }

    @GetMapping("/workspaces/deal/{dealId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResult<DealDetailResponse>> loadDealDetail
            (@PathVariable Long dealId){
        DealCellDetail dealCellDetail = loadDealWithCellUseCase.loadDealWithCellDetail(
                WorkspaceAuthority.from(
                                SecurityContextHolder
                                        .getContext()
                                        .getAuthentication()
                                        .getAuthorities()
                        )
                        .workspaceId()
                        .value(),
                dealId);
        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK,
                DealDetailResponse.builder()
                        .dealCellDetail(dealCellDetail)
                        .build()
                ));
    }

}

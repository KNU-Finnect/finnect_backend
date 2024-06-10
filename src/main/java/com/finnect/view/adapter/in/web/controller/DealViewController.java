package com.finnect.view.adapter.in.web.controller;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.crm.application.port.in.deal.LoadDealWithCellUseCase;
import com.finnect.crm.domain.deal.DealCell;
import com.finnect.common.vo.WorkspaceAuthority;
import com.finnect.view.adapter.in.web.res.DealViewInfoResponse;
import com.finnect.view.adapter.in.web.res.SimpleViewInfosResponse;
import com.finnect.view.application.port.in.LoadViewUseCase;
import com.finnect.view.domain.View;
import com.finnect.view.domain.ViewDetail;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DealViewController {
    private final LoadViewUseCase loadViewUseCase;
    private final LoadDealWithCellUseCase loadDealWithCellUseCase;


    @GetMapping("/workspaces/deals/views/{viewId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResult<DealViewInfoResponse>> getView(
            @PathVariable Long viewId,
            @RequestParam(required = true) int page
    ){
        ViewDetail viewDetail = loadViewUseCase.loadViewInfo(View.builder()
                        .viewId(viewId)
                        .build()
        );

        List<DealCell> dealCells = loadDealWithCellUseCase.loadDealWithCell(
                WorkspaceAuthority.from(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getAuthorities()).workspaceId().value()
                , viewDetail.getFilters(), page);

        return new ResponseEntity<>(ApiUtils.success(HttpStatus.OK, new DealViewInfoResponse(viewDetail, dealCells))
                , HttpStatus.OK);
    }

    @GetMapping("/workspaces/deals/views/origin")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResult<DealViewInfoResponse>> getDefaultDealView(
            @RequestParam(required = true) int page
    ){
        log.info(String.valueOf(WorkspaceAuthority.from(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()).workspaceId().value()));
        ViewDetail viewDetail = loadViewUseCase.loadDealDefaultView(
                        WorkspaceAuthority.from(SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getAuthorities()).workspaceId().value());
        List<DealCell> dealCells = loadDealWithCellUseCase.loadDealWithCell(
                WorkspaceAuthority.from(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getAuthorities()).workspaceId().value(), null, page);
        return new ResponseEntity<>(ApiUtils.success(HttpStatus.OK, new DealViewInfoResponse(viewDetail, dealCells))
                , HttpStatus.OK);
    }

    @GetMapping("/workspaces/deals/views")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResult<SimpleViewInfosResponse>> getViewList(
    ){
        var views = loadViewUseCase.loadDealViewList(
                WorkspaceAuthority.from(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getAuthorities()).workspaceId().value()
        );
        return new ResponseEntity<>(ApiUtils.success(HttpStatus.OK, new SimpleViewInfosResponse(views))
                , HttpStatus.OK);
    }
}

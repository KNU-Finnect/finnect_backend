package com.finnect.view.adapter.in.web.controller;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.crm.application.port.in.company.LoadCompanyWithCellUseCase;
import com.finnect.crm.domain.company.CompanyCell;
import com.finnect.common.vo.WorkspaceAuthority;
import com.finnect.view.adapter.in.web.req.FilterRequest;
import com.finnect.view.adapter.in.web.res.CompanyViewInfoResponse;
import com.finnect.view.adapter.in.web.res.SimpleViewInfosResponse;
import com.finnect.view.application.port.in.CreateViewUseCase;
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
@RequiredArgsConstructor
@Slf4j
public class CompanyViewController {
    private final CreateViewUseCase createViewUseCase;
    private final LoadViewUseCase loadViewUseCase;
    private final LoadCompanyWithCellUseCase loadCompanyWithCellUseCase;

    @GetMapping("/workspaces/companies/views")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResult<SimpleViewInfosResponse>> getViewList(
    ){
        var views = loadViewUseCase.loadCompanyViewList(
                WorkspaceAuthority.from(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getAuthorities()).workspaceId().value()
        );
        return new ResponseEntity<>(ApiUtils.success(HttpStatus.OK, new SimpleViewInfosResponse(views))
                , HttpStatus.OK);
    }

    @GetMapping("/workspaces/companies/views/origin")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResult<CompanyViewInfoResponse>> getDefaultDealView(
            @RequestParam(required = true) int page
    ){
        log.info(String.valueOf(WorkspaceAuthority.from(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()).workspaceId().value()));
        ViewDetail viewDetail = loadViewUseCase.loadCompanyDefaultView(
                WorkspaceAuthority.from(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getAuthorities()).workspaceId().value());
        List<CompanyCell> companyCells = loadCompanyWithCellUseCase.loadCompanyWithCell(
                WorkspaceAuthority.from(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getAuthorities()).workspaceId().value(),
                viewDetail.getFilters(), page);
        return new ResponseEntity<>(ApiUtils.success(HttpStatus.OK, new CompanyViewInfoResponse(viewDetail, companyCells))
                , HttpStatus.OK);
    }

    @GetMapping("/workspaces/companies/views/{viewId}")
    public ResponseEntity<ApiResult<CompanyViewInfoResponse>> getView(
            @PathVariable Long viewId,
            @RequestParam(required = false) List<FilterRequest> filters,
            @RequestParam(required = true) int page
    ){
        ViewDetail viewDetail = loadViewUseCase.loadViewInfo(View.builder()
                        .viewId(viewId)
                        .workspaceId(
                                WorkspaceAuthority.from(SecurityContextHolder
                                        .getContext()
                                        .getAuthentication()
                                        .getAuthorities())
                                .workspaceId().value()
                        )
                        .build()
        );

        var companyCell = loadCompanyWithCellUseCase.loadCompanyWithCell(
                WorkspaceAuthority.from(SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getAuthorities())
                        .workspaceId()
                        .value(),
                viewDetail.getFilters(),
                page
        );
        return new ResponseEntity<>(ApiUtils.success(HttpStatus.OK, new CompanyViewInfoResponse(viewDetail, companyCell))
                , HttpStatus.OK);
    }
}

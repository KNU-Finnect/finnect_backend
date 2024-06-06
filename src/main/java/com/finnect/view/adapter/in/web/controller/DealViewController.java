package com.finnect.view.adapter.in.web.controller;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.crm.application.port.in.cell.LoadDealWithCellUseCase;
import com.finnect.crm.domain.deal.DealCell;
import com.finnect.user.vo.WorkspaceAuthority;
import com.finnect.view.adapter.in.web.req.CreateViewRequest;
import com.finnect.view.adapter.in.web.req.FilterRequest;
import com.finnect.view.adapter.in.web.res.CreateViewResponse;
import com.finnect.view.adapter.in.web.res.SimpleViewInfosResponse;
import com.finnect.view.adapter.in.web.res.ViewInfoResponse;
import com.finnect.view.application.port.in.CreateViewUseCase;
import com.finnect.view.application.port.in.LoadViewUseCase;
import com.finnect.view.domain.Filter;
import com.finnect.view.domain.View;
import com.finnect.view.domain.ViewDetail;
import com.finnect.view.domain.state.ViewState;
import java.util.Collections;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DealViewController {
    private final CreateViewUseCase createViewUseCase;
    private final LoadViewUseCase loadViewUseCase;
    private final LoadDealWithCellUseCase loadDealWithCellUseCase;
    @PostMapping("/workspaces/views")
    public ResponseEntity<ApiResult<CreateViewResponse>> createView(@RequestBody CreateViewRequest request){

        log.info(request.toString());
        ViewState newView = createViewUseCase.createNewView(request.toDomain());
        return new ResponseEntity<>(
                ApiUtils.success(HttpStatus.CREATED, new CreateViewResponse(newView)),
                HttpStatus.CREATED);
    }

    @GetMapping("/workspaces/deals/views/{viewId}")
    public ResponseEntity<ApiResult<ViewInfoResponse>> getView(
            @PathVariable Long viewId,
            @RequestParam(required = false) List<FilterRequest> filters,
            @RequestParam(required = true) int page
    ){
        List<Filter> filterList = (filters == null) ? Collections.emptyList() : filters.stream()
                .map((filter) -> Filter.builder()
                        .columnId(filter.getColumnId())
                        .value(filter.getValue())
                        .filterCondition(filter.getFilterCondition())
                        .build())
                .toList();
        ViewDetail viewDetail = loadViewUseCase.loadViewInfo(View.builder()
                        .viewId(viewId)
                        .build(),
                filterList);

        log.info(viewDetail.toString());
        List<DealCell> dealCells = loadDealWithCellUseCase.loadDealWithCell(1L, viewDetail.getFilters(), page);

        return new ResponseEntity<>(ApiUtils.success(HttpStatus.OK, new ViewInfoResponse(viewDetail, dealCells))
                , HttpStatus.OK);
    }

    @GetMapping("/workspaces/deals/views/origin")
    public ResponseEntity<ApiResult<ViewInfoResponse>> getDefaultDealView(
    ){

        return new ResponseEntity<>(ApiUtils.success(HttpStatus.OK, null)
                , HttpStatus.OK);
    }

    @GetMapping("/workspaces/deals/views")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResult<SimpleViewInfosResponse>> getViewList(
    ){
        var views = loadViewUseCase.loadViewList(
                WorkspaceAuthority.from(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getAuthorities()).workspaceId().value()
        );
        return new ResponseEntity<>(ApiUtils.success(HttpStatus.OK, new SimpleViewInfosResponse(views))
                , HttpStatus.OK);
    }
}

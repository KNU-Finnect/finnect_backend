package com.finnect.view.adapter.in.web.controller;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.view.adapter.in.web.req.CreateViewRequest;
import com.finnect.view.adapter.in.web.req.PatchFilterRequest;
import com.finnect.view.adapter.in.web.req.PatchViewColumnRequest;
import com.finnect.view.adapter.in.web.req.PatchViewRequest;
import com.finnect.view.adapter.in.web.res.CreateViewResponse;
import com.finnect.view.application.port.in.CreateViewUseCase;
import com.finnect.view.application.port.in.ModifyViewUseCase;
import com.finnect.view.domain.Filter;
import com.finnect.view.domain.ViewColumn;
import com.finnect.view.domain.state.ViewState;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ViewController {
    private final CreateViewUseCase createViewUseCase;
    private final ModifyViewUseCase modifyViewUseCase;
    @PostMapping("/workspaces/views")
    public ResponseEntity<ApiResult<CreateViewResponse>> createView(
            @RequestBody CreateViewRequest request){

        log.info(request.toString());
        ViewState newView = createViewUseCase.createNewView(request.toDomain());
        return new ResponseEntity<>(
                ApiUtils.success(HttpStatus.CREATED, new CreateViewResponse(newView)),
                HttpStatus.CREATED);
    }

    @PatchMapping("/workspaces/views")
    public ResponseEntity<ApiResult<String>> patchView(
            @RequestBody PatchViewRequest request){

        log.info(request.toString());
        modifyViewUseCase.patchViewName(request.getViewId(), request.getViewName());
        return new ResponseEntity<>(
                ApiUtils.success(HttpStatus.CREATED, "Modified View Name"),
                HttpStatus.CREATED);
    }

    @Operation(
            summary = "Filter추가 API",
            description = "Filter를 추가합니다. 기존에 있던 필터는 모두 초기화됩니다."
    )
    @PatchMapping("/workspaces/views/filters")
    public ResponseEntity<ApiResult<String>> patchFilters(
            @RequestBody PatchFilterRequest request){

        var filters = request.getFilters();
        modifyViewUseCase.patchViewFilters(request.getViewId(),
                filters == null ? Collections.emptyList() : filters.stream()
                        .map((filter) -> Filter.builder()
                                .columnId(filter.getColumnId())
                                .value(filter.getValue())
                                .filterCondition(filter.getFilterCondition())
                                .build())
                        .toList()
        );

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, "filter changed"));
    }

    @Operation(
            summary = "Column정보를 변경합니다.",
            description = "Column 정보를 변환합니다."
    )
    @PatchMapping("/workspaces/views/columns")
    public ResponseEntity<ApiResult<String>> patchColumn(
            @RequestBody PatchViewColumnRequest request){

        modifyViewUseCase.patchViewColumn(request.getViewId(),
               ViewColumn.builder()
                    .columnId(request.getColumn().getColumnId())
                    .hided(request.getColumn().isHided())
                    .sorting(request.getColumn().getSorting())
                    .index(request.getColumn().getIndex())
                .build()
        );

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, "filter changed"));
    }
}

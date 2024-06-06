package com.finnect.view.adapter.in.web.controller;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.crm.application.port.in.cell.LoadDealWithCellUseCase;
import com.finnect.view.adapter.in.web.req.FilterRequest;
import com.finnect.view.adapter.in.web.res.CompanyViewInfoResponse;
import com.finnect.view.application.port.in.CreateViewUseCase;
import com.finnect.view.application.port.in.LoadViewUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final LoadDealWithCellUseCase loadDealWithCellUseCase;


    @GetMapping("/workspaces/companies/views/{viewId}")
    public ResponseEntity<ApiResult<CompanyViewInfoResponse>> getView(
            @PathVariable Long viewId,
            @RequestParam(required = false) List<FilterRequest> filters,
            @RequestParam(required = true) int page
    ){


        return new ResponseEntity<>(ApiUtils.success(HttpStatus.OK, new CompanyViewInfoResponse(null, null))
                , HttpStatus.OK);
    }
}

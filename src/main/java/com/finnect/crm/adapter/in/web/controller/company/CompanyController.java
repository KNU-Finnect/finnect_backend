package com.finnect.crm.adapter.in.web.controller.company;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.crm.adapter.in.web.req.company.CreateCompanyRequest;
import com.finnect.crm.adapter.in.web.res.company.CompanyDto;
import com.finnect.crm.adapter.in.web.res.company.CreateCompanyResponse;
import com.finnect.crm.adapter.in.web.res.company.LoadCompaniesResponse;
import com.finnect.crm.application.port.in.company.CreateCompanyCommand;
import com.finnect.crm.application.port.in.company.CreateCompanyUsecase;
import com.finnect.crm.application.port.in.company.LoadCompanyUseCase;
import com.finnect.crm.domain.company.CompanyState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CreateCompanyUsecase createCompanyUsecase;
    private final LoadCompanyUseCase loadCompanyUseCase;

    @PostMapping("/workspaces/companies")
    public ResponseEntity<ApiUtils.ApiResult<CreateCompanyResponse>> createCompany(@RequestBody CreateCompanyRequest request) {

        CompanyState state = createCompanyUsecase.createCompany(
                new CreateCompanyCommand(1L, request.getDomain(), request.getCompanyName())
        );

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiUtils.success(HttpStatus.OK, CreateCompanyResponse.of(CompanyDto.from(state)))
        );
    }

    @GetMapping("/workspaces/companies")
    public ResponseEntity<ApiResult<LoadCompaniesResponse>> loadCompany() {
        List<CompanyState> companies =
                loadCompanyUseCase.loadCompaniesByWorkspaceId(1L);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiUtils.success(HttpStatus.OK,
                    new LoadCompaniesResponse(
                        companies.stream()
                        .map(CompanyDto::from)
                    .toList()
                ))
        );
    }
}

package com.finnect.crm.adapter.in.web.controller.company;

import com.finnect.common.ApiUtils;
import com.finnect.crm.adapter.in.web.req.company.CreateCompanyRequest;
import com.finnect.crm.adapter.in.web.res.company.CompanyDto;
import com.finnect.crm.adapter.in.web.res.company.CreateCompanyResponse;
import com.finnect.crm.application.port.in.company.CreateCompanyCommand;
import com.finnect.crm.application.port.in.company.CreateCompanyUsecase;
import com.finnect.crm.domain.company.CompanyState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CreateCompanyUsecase createCompanyUsecase;

    @PostMapping("/workspaces/companies")
    public ResponseEntity<ApiUtils.ApiResult<CreateCompanyResponse>> createCompany(@RequestBody CreateCompanyRequest request) {

        CompanyState state = createCompanyUsecase.createCompany(
                new CreateCompanyCommand(1L, request.getDomain(), request.getCompanyName())
        );

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiUtils.success(HttpStatus.OK, CreateCompanyResponse.of(CompanyDto.from(state)))
        );
    }
}

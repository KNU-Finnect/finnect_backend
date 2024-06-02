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
import com.finnect.user.vo.WorkspaceAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CreateCompanyUsecase createCompanyUsecase;
    private final LoadCompanyUseCase loadCompanyUseCase;

    @PreAuthorize("permitAll()")
    @PostMapping("/workspaces/companies")
    public ResponseEntity<ApiUtils.ApiResult<CreateCompanyResponse>> createCompany(@RequestBody CreateCompanyRequest request) {
        Long workspaceId;
        try {
            workspaceId = WorkspaceAuthority.from(
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities()
            ).workspaceId().value();
        } catch (Exception e) {
            throw new RuntimeException("워크스페이스 ID가 누락되었습니다.");
        }

        CompanyState state = createCompanyUsecase.createCompany(
                new CreateCompanyCommand(workspaceId, request.getDomain(), request.getCompanyName())
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

package com.finnect.crm.adapter.in.web.controller.company;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.crm.adapter.in.web.req.company.CreateCompanyRequest;
import com.finnect.crm.adapter.in.web.res.company.CompanyDto;
import com.finnect.crm.adapter.in.web.res.company.CreateCompanyResponse;
import com.finnect.crm.adapter.in.web.res.company.GetCompanyResponse;
import com.finnect.crm.adapter.in.web.res.company.LoadCompaniesResponse;
import com.finnect.crm.application.port.in.company.CreateCompanyCommand;
import com.finnect.crm.application.port.in.company.CreateCompanyUsecase;
import com.finnect.crm.application.port.in.company.LoadCompanyUseCase;
import com.finnect.crm.domain.company.CompanyDetail;
import com.finnect.crm.domain.company.CompanyState;
import java.util.List;
import com.finnect.user.vo.WorkspaceAuthority;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workspaces/companies")
public class CompanyController {

    private final CreateCompanyUsecase createCompanyUsecase;
    private final LoadCompanyUseCase loadCompanyUseCase;

    @Operation(
            summary = "Company 생성 API",
            description = """
                    주어진 Company에 대한 정보와 access token의 workspace id로 Company를 생성합니다.
                    domain은 서로 같은 워크스페이스 내에서 중복될 수 없습니다."""
    )
    @PreAuthorize("isAuthenticated()")
    @PostMapping
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

    @Operation(
            summary = "Company 배열 조회 API",
            description = "주어진 access token의 workspace id로 Workspace에 속한 Company의 배열을 반환합니다."
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<ApiResult<LoadCompaniesResponse>> getCompanies() {
        Long workspaceId;
        try {
            workspaceId = WorkspaceAuthority.from(
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities()
            ).workspaceId().value();
        } catch (Exception e) {
            throw new RuntimeException("워크스페이스 ID가 누락되었습니다.");
        }

        List<CompanyState> companies =
                loadCompanyUseCase.loadCompaniesByWorkspaceId(workspaceId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiUtils.success(HttpStatus.OK,
                    LoadCompaniesResponse.of(
                        companies.stream()
                            .map(CompanyDto::from)
                            .toList()
                ))
        );
    }

    @Operation(
            summary = "Company 상세 조회 API",
            description = "주어진 company id로 Company의 상세 정보를 조회합니다."
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{companyId}")
    public ResponseEntity<ApiResult<GetCompanyResponse>> getCompany(@PathVariable Long companyId) {

        CompanyDetail companyDetail = loadCompanyUseCase.loadCompanyDetail(companyId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiUtils.success(
                        HttpStatus.OK,
                        GetCompanyResponse.from(companyDetail)
                ));
    }
}

package com.finnect.crm.application.service.company;

import com.finnect.crm.application.port.in.company.CreateCompanyCommand;
import com.finnect.crm.application.port.in.company.CreateCompanyUsecase;
import com.finnect.crm.application.port.out.company.SaveCompanyPort;
import com.finnect.crm.application.port.out.company.SearchCompanyPort;
import com.finnect.crm.domain.company.CompanyState;
import com.finnect.crm.domain.company.CompanyWithoutId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
class CreateCompanyService implements CreateCompanyUsecase {

    private final SearchCompanyPort searchCompanyPort;
    private final SaveCompanyPort saveCompanyPort;

    @Override
    public CompanyState createCompany(CreateCompanyCommand cmd) {
        if (searchCompanyPort.searchByDomain(cmd.getDomain()))
            throw new RuntimeException(cmd.getDomain() + " 도메인이 이미 존재합니다.");

        CompanyWithoutId companyWithoutId = CompanyWithoutId.builder()
                .domain(cmd.getDomain())
                .companyName(cmd.getCompanyName())
                .workspaceId(cmd.getWorkspaceId())
                .build();

        return saveCompanyPort.save(companyWithoutId);
    }
}

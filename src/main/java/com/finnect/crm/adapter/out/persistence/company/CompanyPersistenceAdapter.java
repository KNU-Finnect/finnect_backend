package com.finnect.crm.adapter.out.persistence.company;

import com.finnect.common.error.NotFoundException;
import com.finnect.crm.application.port.out.company.CheckCompanyQuery;
import com.finnect.crm.application.port.out.company.LoadCompanyPort;
import com.finnect.crm.application.port.out.company.SaveCompanyPort;
import com.finnect.crm.domain.company.CompanyState;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CompanyPersistenceAdapter
        implements SaveCompanyPort, LoadCompanyPort, CheckCompanyQuery {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyState save(CompanyState state) {
        return companyRepository.save(CompanyEntity.from(state));
    }

    @Override
    public boolean checkDomainExists(Long workspaceId, String domain) {
        return companyRepository.findByWorkspaceIdAndDomain(workspaceId, domain).isPresent();
    }

    @Override
    public CompanyState loadById(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(
                () -> new NotFoundException("존재하지 않는 company입니다.")
        );
    }

    @Override
    public List<CompanyState> loadCompaniesByWorkspaceId(Long workspaceId) {
        List<CompanyEntity> companies = companyRepository.findCompanyEntitiesByWorkspaceId(workspaceId);
        return new ArrayList<>(companies);
    }


    @Override
    public boolean checkCompanyExists(Long companyId) {
        return companyRepository.existsById(companyId);
    }
}

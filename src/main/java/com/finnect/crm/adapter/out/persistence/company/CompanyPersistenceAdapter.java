package com.finnect.crm.adapter.out.persistence.company;

import com.finnect.crm.application.port.out.company.LoadCompanyPort;
import com.finnect.crm.application.port.out.company.SaveCompanyPort;
import com.finnect.crm.application.port.out.company.SearchCompanyPort;
import com.finnect.crm.domain.company.CompanyState;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CompanyPersistenceAdapter
        implements SaveCompanyPort, SearchCompanyPort ,
        LoadCompanyPort {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyState save(CompanyState state) {
        return companyRepository.save(CompanyEntity.from(state));
    }

    @Override
    public boolean searchByDomain(String domain) {
        return companyRepository.findByDomain(domain).isPresent();
    }

    @Override
    public CompanyState loadById(Long companyId) {
        return null;
    }

    @Override
    public List<CompanyState> loadCompaniesByWorkspaceId(Long workspaceId) {
        List<CompanyEntity> companies = companyRepository.findCompanyEntitiesByWorkspaceId(workspaceId);
        return new ArrayList<>(companies);
    }
}

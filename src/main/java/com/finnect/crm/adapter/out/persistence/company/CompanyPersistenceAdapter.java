package com.finnect.crm.adapter.out.persistence.company;

import com.finnect.crm.application.port.out.company.SaveCompanyPort;
import com.finnect.crm.application.port.out.company.SearchCompanyPort;
import com.finnect.crm.domain.company.CompanyState;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CompanyPersistenceAdapter
        implements SaveCompanyPort, SearchCompanyPort {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyState save(CompanyState state) {
        return companyRepository.save(CompanyEntity.from(state));
    }

    @Override
    public boolean searchByDomain(String domain) {
        return companyRepository.findByDomain(domain).isPresent();
    }
}

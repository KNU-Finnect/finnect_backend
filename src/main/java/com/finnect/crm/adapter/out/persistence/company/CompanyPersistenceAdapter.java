package com.finnect.crm.adapter.out.persistence.company;

import com.finnect.crm.application.port.out.company.SaveCompanyPort;
import com.finnect.crm.domain.company.CompanyState;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CompanyPersistenceAdapter
        implements SaveCompanyPort {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyState save(CompanyState state) {
        return companyRepository.save(CompanyEntity.from(state));
    }
}

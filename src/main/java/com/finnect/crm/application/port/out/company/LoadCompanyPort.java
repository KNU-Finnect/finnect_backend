package com.finnect.crm.application.port.out.company;

import com.finnect.crm.domain.company.CompanyState;

import java.util.Optional;

public interface LoadCompanyPort {

    CompanyState loadById(Long companyId);

    Optional<CompanyState> loadByDomain(String domain);
}

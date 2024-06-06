package com.finnect.crm.application.port.out.company;

import com.finnect.crm.domain.company.CompanyState;

public interface SaveCompanyPort {

    CompanyState save(CompanyState companyState);
}

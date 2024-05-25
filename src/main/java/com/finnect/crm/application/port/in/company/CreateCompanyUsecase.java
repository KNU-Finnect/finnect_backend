package com.finnect.crm.application.port.in.company;

import com.finnect.crm.domain.company.CompanyState;

public interface CreateCompanyUsecase {

    CompanyState createCompany(CreateCompanyCommand command);
}

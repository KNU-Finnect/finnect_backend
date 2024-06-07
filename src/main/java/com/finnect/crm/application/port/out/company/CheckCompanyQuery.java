package com.finnect.crm.application.port.out.company;

public interface CheckCompanyQuery {

    boolean checkCompanyExists(Long companyId);

    boolean checkDomainExists(String domain);
}

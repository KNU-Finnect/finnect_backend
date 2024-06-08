package com.finnect.crm.application.port.out.company;

public interface CheckCompanyQuery {

    boolean checkCompanyExists(Long companyId);

    boolean checkDomainExists(Long workspace, String domain);
}

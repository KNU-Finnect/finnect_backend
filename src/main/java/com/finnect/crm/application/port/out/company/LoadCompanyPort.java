package com.finnect.crm.application.port.out.company;

import com.finnect.crm.domain.company.CompanyState;

import java.util.List;

public interface LoadCompanyPort {

    CompanyState loadById(Long companyId);

    List<CompanyState> loadCompaniesByWorkspaceId(Long workspaceId);
}

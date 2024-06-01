package com.finnect.crm.application.port.in.company;

import com.finnect.crm.domain.company.CompanyState;
import java.util.List;

public interface LoadCompanyUseCase {

    List<CompanyState> loadCompaniesByWorkspaceId(Long workspaceId);
}

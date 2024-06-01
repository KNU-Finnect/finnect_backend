package com.finnect.crm.application.service.company;

import com.finnect.crm.application.port.in.company.LoadCompanyUseCase;
import com.finnect.crm.application.port.out.company.LoadCompanyPort;
import com.finnect.crm.domain.company.CompanyState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoadCompanyService implements LoadCompanyUseCase {
    private final LoadCompanyPort loadCompanyPort;

    @Override
    public List<CompanyState> loadCompaniesByWorkspaceId(Long workspaceId) {

        return loadCompanyPort.loadCompaniesByWorkspaceId(workspaceId);
    }
}

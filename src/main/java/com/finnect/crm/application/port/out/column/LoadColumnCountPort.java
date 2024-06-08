package com.finnect.crm.application.port.out.column;

public interface LoadColumnCountPort {
    int loadDealColumnCount(Long workspaceId);

    int loadCompanyColumnCount(Long workspaceId);
}

package com.finnect.crm.domain.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CompanyWithoutId implements CompanyState {

    private final Long workspaceId;
    private final String domain;
    private final String companyName;

    @Override
    public Long getCompanyId() {
        return null;
    }

    @Override
    public Long getDataRowId() {
        return null;
    }
}

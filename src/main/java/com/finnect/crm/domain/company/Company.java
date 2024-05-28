package com.finnect.crm.domain.company;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Company implements CompanyState {

    private final Long companyId;
    private final String domain;
    private final String companyName;
    private final Long workspaceId;
    private final Long dataRowId;

    public static Company from(CompanyState state) {
        return new Company(
                state.getCompanyId(),
                state.getDomain(),
                state.getCompanyName(),
                state.getWorkspaceId(),
                state.getDataRowId()
        );
    }
}

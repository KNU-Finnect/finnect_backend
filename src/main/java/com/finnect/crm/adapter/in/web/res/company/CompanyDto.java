package com.finnect.crm.adapter.in.web.res.company;

import com.finnect.crm.domain.company.CompanyState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyDto {
    private final Long companyId;
    private final String domain;
    private final String companyName;

    public static CompanyDto from(CompanyState state) {
        return new CompanyDto(
                state.getCompanyId(),
                state.getDomain(),
                state.getCompanyName()
        );
    }
}

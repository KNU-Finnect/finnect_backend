package com.finnect.crm.adapter.in.web.res.company;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LoadCompaniesResponse {
    private final List<CompanyDto> companies;

    public static LoadCompaniesResponse of(List<CompanyDto> companies) {
        return new LoadCompaniesResponse(companies);
    }
}

package com.finnect.crm.adapter.in.web.res.company;

import java.util.List;
import lombok.Getter;

@Getter
public class LoadCompaniesResponse {
    private final List<CompanyDto> companies;

    public LoadCompaniesResponse(List<CompanyDto> companies) {
        this.companies = companies;
    }
}

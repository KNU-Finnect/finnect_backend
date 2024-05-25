package com.finnect.crm.adapter.in.web.res.company;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CreateCompanyResponse {

    private final CompanyDto company;

    public static CreateCompanyResponse of(CompanyDto companyDto) {
        return new CreateCompanyResponse(companyDto);
    }
}

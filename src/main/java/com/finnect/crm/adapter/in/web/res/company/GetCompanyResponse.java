package com.finnect.crm.adapter.in.web.res.company;

import com.finnect.crm.domain.company.CompanyDetail;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class GetCompanyResponse {
    private final CompanyDto company;
    private final List<CellDto> cells;

    public static GetCompanyResponse from(CompanyDetail companyDetail) {
        return new GetCompanyResponse(
                CompanyDto.from(companyDetail.getCompany()),
                companyDetail.getCells().stream()
                        .map(CellDto::from)
                        .collect(Collectors.toList()));
    }
}

package com.finnect.crm.adapter.in.web.req.company;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateCompanyRequest {
    private final String domain;
    private final String companyName;
}

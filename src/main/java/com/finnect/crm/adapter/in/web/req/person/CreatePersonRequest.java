package com.finnect.crm.adapter.in.web.req.person;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreatePersonRequest {

    private final Long companyId;
    private final String personName;
    private final String personRole;
    private final String personEmail;
    private final String personPhone;
}

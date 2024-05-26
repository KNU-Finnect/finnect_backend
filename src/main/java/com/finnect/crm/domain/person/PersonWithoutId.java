package com.finnect.crm.domain.person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class PersonWithoutId implements PersonState {

    private final Long companyId;
    private final String personName;
    private final String personRole;
    private final String personEmail;
    private final String personPhone;

    @Override
    public Long getPersonId() {
        return null;
    }
}

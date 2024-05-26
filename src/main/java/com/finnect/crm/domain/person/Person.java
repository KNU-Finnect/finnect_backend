package com.finnect.crm.domain.person;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Person implements PersonState {

    private final Long personId;
    private final Long companyId;
    private final String personName;
    private final String personRole;
    private final String personEmail;
    private final String personPhone;

    public static Person from(PersonState personState) {
        return new Person(
                personState.getPersonId(),
                personState.getCompanyId(),
                personState.getPersonName(),
                personState.getPersonRole(),
                personState.getPersonEmail(),
                personState.getPersonPhone()
        );
    }
}

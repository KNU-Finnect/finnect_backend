package com.finnect.crm.domain.person;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PersonWithCompany implements PersonWithCompanyState {
    private Long personId;
    private Long companyId;
    private String personName;
    private String personRole;
    private String personEmail;
    private String personPhone;
    private String companyName;

    public static PersonWithCompany of(PersonState personState, String companyName) {
        return new PersonWithCompany(
                personState.getPersonId(),
                personState.getCompanyId(),
                personState.getPersonName(),
                personState.getPersonRole(),
                personState.getPersonEmail(),
                personState.getPersonPhone(),
                companyName
        );
    }
}

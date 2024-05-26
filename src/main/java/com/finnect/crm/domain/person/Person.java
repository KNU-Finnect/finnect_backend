package com.finnect.crm.domain.person;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Person implements PersonState {

    private Long personId;
    private Long companyId;
    private String personName;
    private String personRole;
    private String personEmail;
    private String personPhone;

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

    public void rename(String newName) {
        personName = newName;
    }

    public void updateRole(String newRole) {
        personRole = newRole;
    }

    public void updateEmail(String newEmail) {
        personEmail = newEmail;
    }

    public void updatePhone(String newPhone) {
        personPhone = newPhone;
    }
}

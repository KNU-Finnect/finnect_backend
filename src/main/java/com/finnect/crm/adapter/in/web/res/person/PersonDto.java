package com.finnect.crm.adapter.in.web.res.person;

import com.finnect.crm.domain.person.PersonState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PersonDto implements PersonState {

    private final Long personId;
    private final Long companyId;
    private final String personName;
    private final String personRole;
    private final String personEmail;
    private final String personPhone;

    public static PersonDto from(PersonState state) {
        return new PersonDto(
                state.getPersonId(),
                state.getCompanyId(),
                state.getPersonName(),
                state.getPersonRole(),
                state.getPersonEmail(),
                state.getPersonPhone() != null ? state.getPersonPhone() : ""
        );
    }
}

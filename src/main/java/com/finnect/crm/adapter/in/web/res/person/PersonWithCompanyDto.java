package com.finnect.crm.adapter.in.web.res.person;

import com.finnect.crm.domain.person.PersonState;
import com.finnect.crm.domain.person.PersonWithCompanyState;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE) @Builder
@Getter
public class PersonWithCompanyDto implements PersonState {

    private final Long personId;
    private final Long companyId;
    private final String personName;
    private final String personRole;
    private final String personEmail;
    private final String personPhone;
    private final String companyName;

    public static PersonWithCompanyDto from(PersonWithCompanyState state) {
        return PersonWithCompanyDto.builder()
                .personId(state.getPersonId())
                .companyId(state.getCompanyId())
                .personName(state.getPersonName())
                .personRole(state.getPersonRole())
                .personEmail(state.getPersonEmail())
                .personPhone(state.getPersonPhone())
                .companyName(state.getCompanyName())
                .build();
    }
}

package com.finnect.crm.adapter.in.web.res.person;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FindAllPeopleResponse {
    private final List<PersonWithCompanyDto> people;

    public static FindAllPeopleResponse of(List<PersonWithCompanyDto> people) {
        return new FindAllPeopleResponse(people);
    }
}

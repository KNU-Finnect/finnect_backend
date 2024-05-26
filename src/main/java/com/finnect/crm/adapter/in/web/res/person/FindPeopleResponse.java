package com.finnect.crm.adapter.in.web.res.person;

import com.finnect.crm.domain.person.PersonState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FindPeopleResponse {
    private final List<PersonState> people;

    public static FindPeopleResponse of(List<PersonState> people) {
        return new FindPeopleResponse(people);
    }
}

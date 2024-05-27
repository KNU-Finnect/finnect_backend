package com.finnect.crm.adapter.in.web.res.person;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CreatePersonResponse {
    private final PersonDto person;

    public static CreatePersonResponse of(PersonDto state) {
        return new CreatePersonResponse(state);
    }
}

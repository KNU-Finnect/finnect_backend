package com.finnect.crm.adapter.in.web.res.person;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UpdatePersonResponse {
    private final PersonDto person;

    public static UpdatePersonResponse of(PersonDto state) {
        return new UpdatePersonResponse(state);
    }
}

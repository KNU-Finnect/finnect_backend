package com.finnect.user.domain;

import com.finnect.user.state.AccessTokenState;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccessToken implements AccessTokenState {

    private final String token;

    @Override
    public String toString() {
        return token;
    }

    public String toBearerString() {
        return "Bearer %s".formatted(token);
    }
}

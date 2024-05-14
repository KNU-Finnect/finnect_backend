package com.finnect.user.domain;

import com.finnect.user.state.TokenPairState;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder @RequiredArgsConstructor
@Getter
public class TokenPair implements TokenPairState {

    private final AccessToken accessToken;

    private final RefreshToken refreshToken;
}

package com.finnect.user.domain;

import com.finnect.user.state.TokenPairState;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenPair implements TokenPairState {

    private final AccessToken accessToken;

    private final RefreshToken refreshToken;
}

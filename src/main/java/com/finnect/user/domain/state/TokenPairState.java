package com.finnect.user.domain.state;

public interface TokenPairState {

    AccessTokenState getAccessToken();

    RefreshTokenState getRefreshToken();
}

package com.finnect.user.state;

public interface TokenPairState {

    AccessTokenState getAccessToken();

    RefreshTokenState getRefreshToken();
}

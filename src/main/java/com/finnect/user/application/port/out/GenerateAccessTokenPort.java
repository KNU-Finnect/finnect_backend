package com.finnect.user.application.port.out;

import com.finnect.user.domain.state.UserAuthenticationState;

public interface GenerateAccessTokenPort {

    String generateAccessToken(UserAuthenticationState authentication);
}

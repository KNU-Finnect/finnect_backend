package com.finnect.user.application.port.out;

import com.finnect.user.state.UserAuthenticationState;

public interface GenerateAccessTokenPort {

    String generateAccessToken(UserAuthenticationState authentication);
}

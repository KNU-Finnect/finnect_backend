package com.finnect.user.application.port.out;

import com.finnect.user.domain.state.UserAuthenticationState;

public interface ObtainAuthenticationPort {

    UserAuthenticationState obtainAuthentication(String token);
}

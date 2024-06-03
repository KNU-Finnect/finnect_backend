package com.finnect.user.application.port.out;

import com.finnect.user.state.UserAuthenticationState;

public interface ObtainAuthenticationPort {

    UserAuthenticationState obtainAuthentication(String token);
}

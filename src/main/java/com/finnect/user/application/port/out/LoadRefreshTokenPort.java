package com.finnect.user.application.port.out;

import com.finnect.user.application.port.out.exception.RefreshTokenNotFoundException;
import com.finnect.user.state.RefreshTokenState;

public interface LoadRefreshTokenPort {

    RefreshTokenState loadToken(String token) throws RefreshTokenNotFoundException;
}

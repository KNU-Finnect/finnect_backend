package com.finnect.user.application.port.out;

import com.finnect.user.application.port.out.error.RefreshTokenNotFoundException;
import com.finnect.user.domain.state.RefreshTokenState;

public interface LoadRefreshTokenPort {

    RefreshTokenState loadToken(String token) throws RefreshTokenNotFoundException;
}

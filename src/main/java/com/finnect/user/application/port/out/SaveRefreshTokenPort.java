package com.finnect.user.application.port.out;

import com.finnect.user.domain.state.RefreshTokenState;

public interface SaveRefreshTokenPort {

    void saveToken(RefreshTokenState refreshToken);
}

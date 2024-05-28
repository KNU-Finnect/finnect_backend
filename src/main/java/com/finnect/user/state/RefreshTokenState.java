package com.finnect.user.state;

import com.finnect.user.vo.UserId;

public interface RefreshTokenState {

    UserId getUserId();

    Long getExpirationSecond();
}

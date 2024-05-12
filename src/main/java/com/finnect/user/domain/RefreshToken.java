package com.finnect.user.domain;

import com.finnect.user.state.RefreshTokenState;
import com.finnect.user.vo.UserId;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RefreshToken implements RefreshTokenState {

    private final String token;

    private final UserId userId;

    @Override
    public String toString() {
        return token;
    }
}

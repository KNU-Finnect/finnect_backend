package com.finnect.user.domain;

import com.finnect.user.state.RefreshTokenState;
import com.finnect.user.vo.UserId;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder @RequiredArgsConstructor
@Getter
public class RefreshToken implements RefreshTokenState {

    private final String token;

    private final UserId userId;

    @Override
    public String toString() {
        return token;
    }

    public static RefreshToken from(RefreshTokenState refreshToken) {
        return RefreshToken.builder()
                .token(refreshToken.getToken())
                .build();
    }
}

package com.finnect.user.domain;

import com.finnect.user.state.RefreshTokenState;
import com.finnect.user.vo.UserId;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder @RequiredArgsConstructor
public class RefreshToken implements RefreshTokenState {

    private final String token;

    @Getter
    private final UserId userId;

    @Getter
    private final Long expirationSecond;

    @Override
    public String toString() {
        return token;
    }

    public static RefreshToken from(RefreshTokenState refreshToken) {
        return RefreshToken.builder()
                .token(refreshToken.toString())
                .userId(refreshToken.getUserId())
                .expirationSecond(refreshToken.getExpirationSecond())
                .build();
    }
}

package com.finnect.user.vo;

public record JwtPair(
        AccessToken accessToken,
        String refreshToken
) {
}

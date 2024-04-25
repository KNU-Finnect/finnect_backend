package com.finnect.user.adapter.out.jwt;

public record JwtPair(
        AccessToken accessToken,
        RefreshToken refreshToken
) {
}

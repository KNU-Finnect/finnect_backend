package com.finnect.auth.adapter.out.jwt;

public record JwtPair(
        AccessToken accessToken,
        RefreshToken refreshToken
) {
}

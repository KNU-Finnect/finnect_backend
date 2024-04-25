package com.finnect.user.application.security.jwt;

public record JwtPair(
        AccessToken accessToken,
        RefreshToken refreshToken
) {
}

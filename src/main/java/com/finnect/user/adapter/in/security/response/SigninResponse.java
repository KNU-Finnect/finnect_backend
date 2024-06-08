package com.finnect.user.adapter.in.security.response;

import lombok.Builder;

@Builder
public record SigninResponse(
        String refreshToken,
        String personalName
) {
}

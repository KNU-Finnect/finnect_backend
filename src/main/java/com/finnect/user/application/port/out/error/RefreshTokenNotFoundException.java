package com.finnect.user.application.port.out.error;

import com.finnect.common.error.NotFoundException;

public class RefreshTokenNotFoundException extends NotFoundException {

    public RefreshTokenNotFoundException(String refreshToken) {
        super("RefreshToken(%s) is invalid".formatted(refreshToken));
    }
}

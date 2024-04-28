package com.finnect.user.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class CreateAccessTokenCommand {

    @NonNull
    private final String refreshToken;

    public CreateAccessTokenCommand(
            @NonNull String refreshToken
    ) {
        this.refreshToken = refreshToken;
    }
}

package com.finnect.user.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class ReissueCommand {

    @NonNull
    private final String refreshToken;

    public ReissueCommand(
            @NonNull String refreshToken
    ) {
        this.refreshToken = refreshToken;
    }
}

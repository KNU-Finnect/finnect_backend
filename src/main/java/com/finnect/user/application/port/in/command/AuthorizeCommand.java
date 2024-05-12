package com.finnect.user.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class AuthorizeCommand {

    private final String token;

    public AuthorizeCommand(
            @NonNull String token
    ) {
        this.token = token;
    }
}

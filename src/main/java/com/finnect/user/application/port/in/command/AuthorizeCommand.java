package com.finnect.user.application.port.in.command;

import com.finnect.common.SelfValidating;
import lombok.Builder;

@Builder
public class AuthorizeCommand extends SelfValidating<AuthorizeCommand> {

    private final String bearerToken;

    public AuthorizeCommand(
            String bearerToken
    ) {
        this.bearerToken = "%s".formatted(bearerToken);

        validateSelf();
    }

    public String getToken() {
        return bearerToken.replaceFirst("Bearer ", "");
    }
}

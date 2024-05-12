package com.finnect.user.application.port.in.command;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class AuthorizeCommand extends SelfValidating<AuthorizeCommand> {

    @NonNull
    @Pattern(regexp = "^Bearer\\s")
    private final String bearerToken;

    public AuthorizeCommand(
            @NonNull String bearerToken
    ) {
        this.bearerToken = bearerToken;

        validateSelf();
    }

    public String getToken() {
        return bearerToken.replaceFirst("Bearer ", "");
    }
}

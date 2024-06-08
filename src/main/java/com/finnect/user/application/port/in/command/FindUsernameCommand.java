package com.finnect.user.application.port.in.command;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class FindUsernameCommand extends SelfValidating<FindUsernameCommand> {

    @NonNull
    @Email
    private final String email;

    public FindUsernameCommand(
            @NonNull String email
    ) {
        this.email = email;

        validateSelf();
    }
}

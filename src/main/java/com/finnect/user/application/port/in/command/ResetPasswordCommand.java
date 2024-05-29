package com.finnect.user.application.port.in.command;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class ResetPasswordCommand extends SelfValidating<ResetPasswordCommand> {

    @NonNull
    @Email
    private String email;

    public ResetPasswordCommand(
            @NonNull String email
    ) {
        this.email = email;

        validateSelf();
    }
}

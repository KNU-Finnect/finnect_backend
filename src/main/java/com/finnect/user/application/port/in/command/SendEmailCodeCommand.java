package com.finnect.user.application.port.in.command;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class SendEmailCodeCommand extends SelfValidating<SendEmailCodeCommand> {

    @NonNull
    @Email
    private final String email;

    public SendEmailCodeCommand(
            @NonNull String email
    ) {
        this.email = email;

        validateSelf();
    }
}

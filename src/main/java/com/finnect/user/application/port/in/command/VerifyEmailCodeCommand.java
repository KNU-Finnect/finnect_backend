package com.finnect.user.application.port.in.command;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class VerifyEmailCodeCommand extends SelfValidating<VerifyEmailCodeCommand> {

    @NonNull
    @Email
    private final String email;

    private final Integer codeNumber;

    public VerifyEmailCodeCommand(
            @NonNull String email,
            @NonNull Integer codeNumber
    ) {
        this.email = email;
        this.codeNumber = codeNumber;

        validateSelf();
    }
}

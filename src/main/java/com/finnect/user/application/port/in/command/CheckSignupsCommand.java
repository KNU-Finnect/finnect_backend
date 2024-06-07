package com.finnect.user.application.port.in.command;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Builder
@Getter
public class CheckSignupsCommand extends SelfValidating<CheckSignupsCommand> {

    @NonNull @NotEmpty
    private final List<@Email String> emails;

    public CheckSignupsCommand(
            @NonNull List<String> emails
    ) {
        this.emails = emails;

        validateSelf();
    }
}

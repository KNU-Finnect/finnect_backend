package com.finnect.user.application.port.in.command;

import com.finnect.common.SelfValidating;
import com.finnect.user.vo.UserId;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class ChangePasswordCommand extends SelfValidating<ChangePasswordCommand> {

    @NonNull
    private final UserId userId;

    @NonNull
    @Size(min = 10, max = 15) @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[-!*_])[a-zA-Z0-9-_]+$")
    private final String password;

    public ChangePasswordCommand(
            @NonNull UserId userId,
            @NonNull String password
    ) {
        this.userId = userId;
        this.password = password;

        validateSelf();
    }
}

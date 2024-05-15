package com.finnect.user.application.port.in.command;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class SignupCommand extends SelfValidating<SignupCommand> {

    @NonNull
    @Size(min = 5, max = 20) @Pattern(regexp = "^[a-z0-9-_]+$")
    private final String username;

    @NonNull
    @Size(min = 10, max = 15) @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[-_])[a-zA-Z0-9-_]+$")
    private final String password;

    @NonNull
    @Email
    private final String email;

    @NonNull
    @Pattern(regexp = "^[가-힣a-zA-Z]+$")
    private final String firstName;

    @NonNull
    @Pattern(regexp = "^[가-힣a-zA-Z]+$")
    private final String lastName;

    public SignupCommand(
            @NonNull String username,
            @NonNull String password,
            @NonNull String email,
            @NonNull String firstName,
            @NonNull String lastName
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;

        validateSelf();
    }
}

package com.finnect.user.adapter.in.web.request;

import com.finnect.user.application.port.in.command.CreateUserCommand;
import lombok.Getter;

@Getter
public record SignupRequest(
        String username,
        String password,
        String email,
        String firstName,
        String lastName
) implements CreateUserCommand {
}

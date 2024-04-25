package com.finnect.user.adapter.in.web;

import com.finnect.user.application.port.in.CreateUserCommand;
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

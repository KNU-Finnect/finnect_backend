package com.finnect.user.application.port.in.command;

import lombok.Builder;

@Builder
public record CreateUserCommand(
        String username,
        String password,
        String email,
        String firstName,
        String lastName
) {
}

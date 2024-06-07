package com.finnect.user.application.port.in.command;

import com.finnect.common.SelfValidating;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder @RequiredArgsConstructor
@Getter
public class AuthenticateCommand extends SelfValidating<AuthenticateCommand> {

    @NonNull
    private final String username;

    @NonNull
    private final String password;
}
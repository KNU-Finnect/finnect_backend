package com.finnect.user.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder @RequiredArgsConstructor
@Getter
public class AuthorizeCommand {

    @NonNull
    private final String token;
}

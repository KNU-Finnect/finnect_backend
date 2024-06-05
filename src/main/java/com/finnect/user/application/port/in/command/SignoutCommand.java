package com.finnect.user.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder @RequiredArgsConstructor
@Getter
public class SignoutCommand {

    private final String refreshToken;
}

package com.finnect.user.application.port.in.command;

import com.finnect.user.domain.UserAuthentication;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder @RequiredArgsConstructor
@Getter
public class IssueCommand {

    @NonNull
    private final UserAuthentication authentication;
}

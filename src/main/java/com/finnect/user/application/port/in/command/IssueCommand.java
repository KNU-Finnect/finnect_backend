package com.finnect.user.application.port.in.command;

import lombok.*;
import org.springframework.security.core.Authentication;

@Builder @RequiredArgsConstructor
@Getter
public class IssueCommand {

    @NonNull
    private final Authentication authentication;
}

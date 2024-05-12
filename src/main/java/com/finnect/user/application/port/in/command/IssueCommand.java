package com.finnect.user.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.security.core.Authentication;

@Builder
@Getter
public class IssueCommand {

    @NonNull
    private final Authentication authentication;

    public IssueCommand(
            @NonNull Authentication authentication
    ) {
        this.authentication = authentication;
    }
}

package com.finnect.user.application.port.in.command;

import com.finnect.user.domain.UserAuthentication;
import lombok.*;

@Builder @RequiredArgsConstructor
@Getter
public class IssueCommand {

    @NonNull
    private final UserAuthentication authentication;
}

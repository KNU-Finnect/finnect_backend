package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.CreateAccessTokenCommand;
import com.finnect.user.application.security.jwt.AccessToken;

public interface ReissueUseCase {

    AccessToken reissue(CreateAccessTokenCommand command);
}

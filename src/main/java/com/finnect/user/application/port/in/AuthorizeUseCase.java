package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.AuthorizeCommand;
import com.finnect.user.state.UserAuthenticationState;

public interface AuthorizeUseCase {

    UserAuthenticationState authorize(AuthorizeCommand command);
}

package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.AuthenticateCommand;
import com.finnect.user.domain.state.UserAuthenticationState;

public interface AuthenticateUseCase {

    UserAuthenticationState authenticate(AuthenticateCommand command);
}

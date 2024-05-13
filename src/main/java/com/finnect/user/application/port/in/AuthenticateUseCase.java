package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.AuthenticateCommand;
import org.springframework.security.core.Authentication;

public interface AuthenticateUseCase {

    Authentication authenticate(AuthenticateCommand command);
}

package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.AuthorizeCommand;

public interface AuthorizeUseCase {

    void authorize(AuthorizeCommand command);
}

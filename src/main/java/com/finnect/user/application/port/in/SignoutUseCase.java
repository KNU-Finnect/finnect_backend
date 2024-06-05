package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.SignoutCommand;

public interface SignoutUseCase {

    void signout(SignoutCommand command);
}

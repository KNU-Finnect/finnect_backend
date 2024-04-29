package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.SignupCommand;

public interface SignupUseCase {

    void signup(SignupCommand command);
}

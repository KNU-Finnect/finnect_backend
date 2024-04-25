package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.CreateUserCommand;

public interface SignupUseCase {

    Object signup(CreateUserCommand user);
}

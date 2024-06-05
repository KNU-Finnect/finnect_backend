package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.SignupCommand;
import com.finnect.user.application.port.in.error.EmailCodeNotVerifiedException;

public interface SignupUseCase extends VerifyEmailCodeUseCase {

    void signup(SignupCommand command) throws EmailCodeNotVerifiedException;
}

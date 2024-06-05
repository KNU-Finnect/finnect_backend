package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.FindUsernameCommand;
import com.finnect.user.application.port.in.error.EmailCodeNotVerifiedException;

public interface FindUsernameUseCase extends VerifyEmailCodeUseCase {

    String findUsername(FindUsernameCommand command) throws EmailCodeNotVerifiedException;
}

package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.VerifyEmailCodeCommand;

public interface VerifyEmailCodeUseCase {

    void verifyEmailCode(VerifyEmailCodeCommand command);
}

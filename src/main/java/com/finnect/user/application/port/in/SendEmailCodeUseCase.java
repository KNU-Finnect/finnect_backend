package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.SendEmailCodeCommand;

public interface SendEmailCodeUseCase {

    void sendEmailCode(SendEmailCodeCommand command);
}

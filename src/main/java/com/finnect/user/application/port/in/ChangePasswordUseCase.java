package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.ChangePasswordCommand;

public interface ChangePasswordUseCase {

    void changePassword(ChangePasswordCommand command);
}

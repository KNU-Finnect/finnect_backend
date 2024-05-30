package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.ResetPasswordCommand;
import com.finnect.user.application.port.in.exception.EmailCodeNotVerifiedException;

public interface ResetPasswordUseCase extends VerifyEmailCodeUseCase {

    String resetPassword(ResetPasswordCommand command) throws EmailCodeNotVerifiedException;
}

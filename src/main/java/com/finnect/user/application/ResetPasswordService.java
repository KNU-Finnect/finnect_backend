package com.finnect.user.application;

import com.finnect.user.application.port.in.ResetPasswordUseCase;
import com.finnect.user.application.port.in.command.ResetPasswordCommand;
import com.finnect.user.application.port.in.command.VerifyEmailCodeCommand;
import com.finnect.user.application.port.in.error.EmailCodeNotVerifiedException;
import com.finnect.user.application.port.out.*;
import com.finnect.user.domain.EmailCode;
import com.finnect.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ResetPasswordService implements ResetPasswordUseCase {

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    private final LoadEmailCodePort loadEmailCodePort;
    private final SaveEmailCodePort saveEmailCodePort;

    private final GeneratePasswordPort generatePasswordPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void verifyEmailCode(VerifyEmailCodeCommand command) {
        EmailCode emailCode = EmailCode.from(loadEmailCodePort.loadEmailCode(command.getEmail()));
        emailCode.verify(command.getCodeNumber());

        saveEmailCodePort.saveEmailCode(emailCode);

        if (!emailCode.isVerified()) {
            throw new EmailCodeNotVerifiedException(emailCode.getEmail());
        }
    }

    @Override
    public String resetPassword(ResetPasswordCommand command) throws EmailCodeNotVerifiedException {
        EmailCode emailCode = EmailCode.from(loadEmailCodePort.loadEmailCode(command.getEmail()));

        if (!emailCode.isVerified()) {
            throw new EmailCodeNotVerifiedException(emailCode.getEmail());
        }

        User user = User.from(loadUserPort.loadUserByEmail(emailCode.getEmail()));
        String password = generatePasswordPort.generateRandomPassword();
        user.changePassword(passwordEncoder.encode(password));

        updateUserPort.updateUser(user);

        return password;
    }
}

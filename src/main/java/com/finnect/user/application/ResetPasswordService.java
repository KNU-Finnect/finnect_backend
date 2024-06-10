package com.finnect.user.application;

import com.finnect.user.application.port.in.ResetPasswordUseCase;
import com.finnect.user.application.port.in.command.ResetPasswordCommand;
import com.finnect.user.application.port.in.command.VerifyEmailCodeCommand;
import com.finnect.user.application.port.in.error.EmailCodeNotVerifiedException;
import com.finnect.user.application.port.out.*;
import com.finnect.user.domain.EmailCode;
import com.finnect.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService implements ResetPasswordUseCase {

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    private final LoadEmailCodePort loadEmailCodePort;
    private final SaveEmailCodePort saveEmailCodePort;

    private final GeneratePasswordPort generatePasswordPort;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ResetPasswordService(
            LoadUserPort loadUserPort,
            UpdateUserPort updateUserPort,
            LoadEmailCodePort loadEmailCodePort,
            SaveEmailCodePort saveEmailCodePort,
            GeneratePasswordPort generatePasswordPort,
            PasswordEncoder passwordEncoder
    ) {
        this.loadUserPort = loadUserPort;
        this.updateUserPort = updateUserPort;

        this.loadEmailCodePort = loadEmailCodePort;
        this.saveEmailCodePort = saveEmailCodePort;

        this.generatePasswordPort = generatePasswordPort;
        this.passwordEncoder = passwordEncoder;
    }

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

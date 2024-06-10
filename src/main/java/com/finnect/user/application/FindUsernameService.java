package com.finnect.user.application;

import com.finnect.user.application.port.in.FindUsernameUseCase;
import com.finnect.user.application.port.in.command.FindUsernameCommand;
import com.finnect.user.application.port.in.command.VerifyEmailCodeCommand;
import com.finnect.user.application.port.in.error.EmailCodeNotVerifiedException;
import com.finnect.user.application.port.out.LoadEmailCodePort;
import com.finnect.user.application.port.out.LoadUserPort;
import com.finnect.user.application.port.out.SaveEmailCodePort;
import com.finnect.user.domain.EmailCode;
import com.finnect.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUsernameService implements FindUsernameUseCase {

    private final LoadUserPort loadUserPort;

    private final LoadEmailCodePort loadEmailCodePort;
    private final SaveEmailCodePort saveEmailCodePort;

    @Autowired
    public FindUsernameService(
            LoadUserPort loadUserPort,
            LoadEmailCodePort loadEmailCodePort,
            SaveEmailCodePort saveEmailCodePort
    ) {
        this.loadUserPort = loadUserPort;

        this.loadEmailCodePort = loadEmailCodePort;
        this.saveEmailCodePort = saveEmailCodePort;
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
    public String findUsername(FindUsernameCommand command) throws EmailCodeNotVerifiedException {
        EmailCode emailCode = EmailCode.from(loadEmailCodePort.loadEmailCode(command.getEmail()));

        if (!emailCode.isVerified()) {
            throw new EmailCodeNotVerifiedException(emailCode.getEmail());
        }

        User user = User.from(loadUserPort.loadUserByEmail(emailCode.getEmail()));
        return user.getUsername();
    }
}

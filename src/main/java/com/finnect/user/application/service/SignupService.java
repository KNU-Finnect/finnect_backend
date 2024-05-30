package com.finnect.user.application.service;

import com.finnect.user.application.port.in.SignupUseCase;
import com.finnect.user.application.port.in.command.SignupCommand;
import com.finnect.user.application.port.in.command.VerifyEmailCodeCommand;
import com.finnect.user.application.port.in.exception.EmailCodeNotVerifiedException;
import com.finnect.user.application.port.out.CreateUserPort;
import com.finnect.user.application.port.out.LoadEmailCodePort;
import com.finnect.user.application.port.out.SaveEmailCodePort;
import com.finnect.user.domain.EmailCode;
import com.finnect.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService implements SignupUseCase {

    private final CreateUserPort createUserPort;

    private final LoadEmailCodePort loadEmailCodePort;
    private final SaveEmailCodePort saveEmailCodePort;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public SignupService(
            CreateUserPort createUserPort,
            LoadEmailCodePort loadEmailCodePort,
            SaveEmailCodePort saveEmailCodePort,
            PasswordEncoder passwordEncoder
    ) {
        this.createUserPort = createUserPort;

        this.loadEmailCodePort = loadEmailCodePort;
        this.saveEmailCodePort = saveEmailCodePort;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean verifyEmailCode(VerifyEmailCodeCommand command) {
        EmailCode emailCode = EmailCode.from(loadEmailCodePort.loadEmailCode(command.getEmail()));
        emailCode.verify(command.getCodeNumber());

        saveEmailCodePort.saveEmailCode(emailCode);

        return emailCode.isVerified();
    }

    @Override
    public void signup(SignupCommand command) throws EmailCodeNotVerifiedException {
        EmailCode emailCode = EmailCode.from(loadEmailCodePort.loadEmailCode(command.getEmail()));

        if (!emailCode.isVerified()) {
            throw new EmailCodeNotVerifiedException(emailCode.getEmail());
        }

        User user = User.builder()
                .username(command.getUsername())
                .password(passwordEncoder.encode(command.getPassword()))
                .email(command.getEmail())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .build();

        createUserPort.createUser(user);
    }
}

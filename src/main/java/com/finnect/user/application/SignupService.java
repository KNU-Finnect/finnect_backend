package com.finnect.user.application;

import com.finnect.user.application.port.in.SignupUseCase;
import com.finnect.user.application.port.in.command.SignupCommand;
import com.finnect.user.application.port.in.command.VerifyEmailCodeCommand;
import com.finnect.user.application.port.in.error.EmailCodeNotVerifiedException;
import com.finnect.user.application.port.in.error.UserDuplicateException;
import com.finnect.user.application.port.out.CreateUserPort;
import com.finnect.user.application.port.out.ExistsUserPort;
import com.finnect.user.application.port.out.LoadEmailCodePort;
import com.finnect.user.application.port.out.SaveEmailCodePort;
import com.finnect.user.domain.EmailCode;
import com.finnect.user.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService implements SignupUseCase {

    private final ExistsUserPort existsUserPort;
    private final CreateUserPort createUserPort;

    private final LoadEmailCodePort loadEmailCodePort;
    private final SaveEmailCodePort saveEmailCodePort;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public SignupService(
            ExistsUserPort existsUserPort,
            CreateUserPort createUserPort,
            LoadEmailCodePort loadEmailCodePort,
            SaveEmailCodePort saveEmailCodePort,
            PasswordEncoder passwordEncoder
    ) {
        this.existsUserPort = existsUserPort;
        this.createUserPort = createUserPort;

        this.loadEmailCodePort = loadEmailCodePort;
        this.saveEmailCodePort = saveEmailCodePort;

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

    @Transactional
    @Override
    public void signup(SignupCommand command) throws EmailCodeNotVerifiedException {
        EmailCode emailCode = EmailCode.from(loadEmailCodePort.loadEmailCode(command.getEmail()));

        if (!emailCode.isVerified()) {
            throw new EmailCodeNotVerifiedException(emailCode.getEmail());
        }

        if (existsUserPort.existsUserByUsername(command.getUsername())) {
            throw new UserDuplicateException(command.getUsername());
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

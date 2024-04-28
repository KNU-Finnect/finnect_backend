package com.finnect.user.application.service;

import com.finnect.user.application.port.in.command.CreateUserCommand;
import com.finnect.user.application.port.in.SignupUseCase;
import com.finnect.user.application.port.out.CreateUserPort;
import com.finnect.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements SignupUseCase {

    public final CreateUserPort createUserPort;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(
            CreateUserPort createUserPort,
            PasswordEncoder passwordEncoder
    ) {
        this.createUserPort = createUserPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Object signup(CreateUserCommand command) {
        createUserPort.createUser(
                User.from(command, passwordEncoder)
        );
        return null;
    }
}

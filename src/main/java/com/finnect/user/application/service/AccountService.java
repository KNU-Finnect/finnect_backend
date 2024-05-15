package com.finnect.user.application.service;

import com.finnect.user.application.port.in.ChangePasswordUseCase;
import com.finnect.user.application.port.in.SignupUseCase;
import com.finnect.user.application.port.in.command.ChangePasswordCommand;
import com.finnect.user.application.port.in.command.SignupCommand;
import com.finnect.user.application.port.out.CreateUserPort;
import com.finnect.user.application.port.out.LoadUserPort;
import com.finnect.user.application.port.out.UpdateUserPort;
import com.finnect.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements SignupUseCase, ChangePasswordUseCase {

    private final CreateUserPort createUserPort;
    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(
            CreateUserPort createUserPort,
            LoadUserPort loadUserPort,
            UpdateUserPort updateUserPort,
            PasswordEncoder passwordEncoder
    ) {
        this.createUserPort = createUserPort;
        this.loadUserPort = loadUserPort;
        this.updateUserPort = updateUserPort;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signup(SignupCommand command) {
        User user = User.builder()
                .username(command.getUsername())
                .password(passwordEncoder.encode(command.getPassword()))
                .email(command.getEmail())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .build();

        createUserPort.createUser(user);
    }

    @Override
    public void changePassword(ChangePasswordCommand command) {
        User user = User.from(loadUserPort.loadUser(command.getUserId()));
        user.changePassword(passwordEncoder.encode(command.getPassword()));

        updateUserPort.updateUser(user);
    }
}

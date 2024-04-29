package com.finnect.user.application.service;

import com.finnect.user.application.port.in.SignupUseCase;
import com.finnect.user.application.port.in.command.SignupCommand;
import com.finnect.user.application.port.out.CreateUserPort;
import com.finnect.user.domain.User;
import com.finnect.user.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements SignupUseCase {

    private final CreateUserPort createUserPort;

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
    public void signup(SignupCommand command) {
        UserInfo userInfo = UserInfo.builder()
                .email(command.getEmail())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .build();

        User user = User.builder()
                .username(command.getUsername())
                .password(passwordEncoder.encode(command.getPassword()))
                .userInfo(userInfo)
                .build();

        createUserPort.createUser(user);
    }
}

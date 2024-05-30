package com.finnect.user.application.service;

import com.finnect.user.application.port.in.ChangePasswordUseCase;
import com.finnect.user.application.port.in.command.ChangePasswordCommand;
import com.finnect.user.application.port.out.LoadUserPort;
import com.finnect.user.application.port.out.UpdateUserPort;
import com.finnect.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements ChangePasswordUseCase {

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserInfoService(
            LoadUserPort loadUserPort,
            UpdateUserPort updateUserPort,
            PasswordEncoder passwordEncoder
    ) {
        this.loadUserPort = loadUserPort;
        this.updateUserPort = updateUserPort;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void changePassword(ChangePasswordCommand command) {
        User user = User.from(loadUserPort.loadUser(command.getUserId()));
        user.changePassword(passwordEncoder.encode(command.getPassword()));

        updateUserPort.updateUser(user);
    }
}

package com.finnect.user.application.service;

import com.finnect.user.application.port.in.ChangeDefaultWorkspaceUseCase;
import com.finnect.user.application.port.in.ChangePasswordUseCase;
import com.finnect.user.application.port.in.CheckDefaultWorkspaceQuery;
import com.finnect.user.application.port.in.GetPersonalNameQuery;
import com.finnect.user.application.port.in.command.ChangeDefaultWorkspaceCommand;
import com.finnect.user.application.port.in.command.ChangePasswordCommand;
import com.finnect.user.application.port.out.LoadUserPort;
import com.finnect.user.application.port.out.UpdateUserPort;
import com.finnect.user.domain.User;
import com.finnect.user.vo.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements
        GetPersonalNameQuery, ChangePasswordUseCase, CheckDefaultWorkspaceQuery, ChangeDefaultWorkspaceUseCase
{
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
    public String getPersonalName(UserId userId) {
        User user = User.from(loadUserPort.loadUser(userId));

        return user.getLastName() + user.getFirstName();
    }

    @Override
    public void changePassword(ChangePasswordCommand command) {
        User user = User.from(loadUserPort.loadUser(command.getUserId()));
        user.changePassword(passwordEncoder.encode(command.getPassword()));

        updateUserPort.updateUser(user);
    }

    @Override
    public void changeDefaultWorkspace(ChangeDefaultWorkspaceCommand command) {
        User user = User.from(loadUserPort.loadUser(command.getUserId()));
        user.changeDefaultWorkspace(command.getWorkspaceId());

        updateUserPort.updateUser(user);
    }

    @Override
    public boolean checkDefaultWorkspace(UserId userId) {
        User user = User.from(loadUserPort.loadUser(userId));

        return user.hasDefaultWorkspace();
    }
}

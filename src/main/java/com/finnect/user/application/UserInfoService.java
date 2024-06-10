package com.finnect.user.application;

import com.finnect.user.application.port.in.ChangeDefaultWorkspaceUseCase;
import com.finnect.user.application.port.in.ChangePasswordUseCase;
import com.finnect.user.application.port.in.CheckDefaultWorkspaceQuery;
import com.finnect.user.application.port.in.CheckSignupQuery;
import com.finnect.user.application.port.in.GetPersonalNameQuery;
import com.finnect.user.application.port.in.command.ChangeDefaultWorkspaceCommand;
import com.finnect.user.application.port.in.command.ChangePasswordCommand;
import com.finnect.user.application.port.in.command.CheckSignupsCommand;
import com.finnect.user.application.port.out.ExistsUserPort;
import com.finnect.user.application.port.out.LoadUserPort;
import com.finnect.user.application.port.out.UpdateUserPort;
import com.finnect.user.domain.User;
import com.finnect.common.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserInfoService implements
        GetPersonalNameQuery,
        ChangePasswordUseCase,
        CheckSignupQuery,
        CheckDefaultWorkspaceQuery,
        ChangeDefaultWorkspaceUseCase
{
    private final ExistsUserPort existsUserPort;
    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    private final PasswordEncoder passwordEncoder;

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
    public Map<String, Boolean> checkSignups(CheckSignupsCommand command) {
        Map<String, Boolean> emailsExists = new HashMap<>();

        for (String email: command.getEmails()) {
            emailsExists.put(email, existsUserPort.existsUserByEmail(email));
        }

        return emailsExists;
    }

    @Override
    public boolean checkDefaultWorkspace(UserId userId) {
        User user = User.from(loadUserPort.loadUser(userId));

        return user.hasDefaultWorkspace();
    }
}

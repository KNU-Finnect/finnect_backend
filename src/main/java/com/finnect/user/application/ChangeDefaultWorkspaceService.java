package com.finnect.user.application;

import com.finnect.user.application.port.in.ChangeDefaultWorkspaceUseCase;
import com.finnect.user.application.port.in.command.ChangeDefaultWorkspaceCommand;
import com.finnect.user.application.port.out.LoadUserPort;
import com.finnect.user.application.port.out.UpdateUserPort;
import com.finnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeDefaultWorkspaceService implements ChangeDefaultWorkspaceUseCase {

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    @Override
    public void changeDefaultWorkspace(ChangeDefaultWorkspaceCommand command) {
        User user = User.from(loadUserPort.loadUser(command.getUserId()));
        user.changeDefaultWorkspace(command.getWorkspaceId());

        updateUserPort.updateUser(user);
    }
}

package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.ChangeDefaultWorkspaceCommand;

public interface ChangeDefaultWorkspaceUseCase {

    void changeDefaultWorkspace(ChangeDefaultWorkspaceCommand command);
}

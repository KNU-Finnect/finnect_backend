package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.ChangeDefaultWorkspaceCommand;
import com.finnect.user.vo.UserId;
import com.finnect.user.vo.WorkspaceId;

public interface ChangeDefaultWorkspaceUseCase {

    void changeDefaultWorkspace(ChangeDefaultWorkspaceCommand command);
}

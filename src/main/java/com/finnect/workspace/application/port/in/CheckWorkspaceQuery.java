package com.finnect.workspace.application.port.in;

import com.finnect.user.vo.UserId;
import com.finnect.user.vo.WorkspaceId;

public interface CheckWorkspaceQuery {

    boolean checkWorkspace(UserId userId, WorkspaceId workspaceId);
}

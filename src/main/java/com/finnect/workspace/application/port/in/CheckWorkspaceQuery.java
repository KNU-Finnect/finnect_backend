package com.finnect.workspace.application.port.in;

import com.finnect.common.vo.UserId;
import com.finnect.common.vo.WorkspaceId;

public interface CheckWorkspaceQuery {

    boolean checkWorkspace(UserId userId, WorkspaceId workspaceId);
}

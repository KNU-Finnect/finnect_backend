package com.finnect.user.state;

import com.finnect.user.vo.UserId;
import com.finnect.user.vo.WorkspaceId;

public interface RefreshTokenState {

    UserId getUserId();

    WorkspaceId getWorkspaceId();

    Long getExpirationSecond();
}

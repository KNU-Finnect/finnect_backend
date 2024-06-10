package com.finnect.user.state;

import com.finnect.common.vo.UserId;
import com.finnect.common.vo.WorkspaceId;

public interface RefreshTokenState {

    UserId getUserId();

    WorkspaceId getWorkspaceId();

    Long getExpirationSecond();
}

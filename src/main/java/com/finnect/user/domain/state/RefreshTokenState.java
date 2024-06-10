package com.finnect.user.domain.state;

import com.finnect.common.vo.UserId;
import com.finnect.common.vo.WorkspaceId;

public interface RefreshTokenState {

    UserId getUserId();

    WorkspaceId getWorkspaceId();

    Long getExpirationSecond();
}

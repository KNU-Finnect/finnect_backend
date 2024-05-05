package com.finnect.user.state;

import com.finnect.user.vo.UserId;
import com.finnect.user.vo.WorkspaceId;

public interface UserInfoState {

    UserId getId();

    String getEmail();

    String getFirstName();

    String getLastName();

    WorkspaceId getDefaultWorkspaceId();
}

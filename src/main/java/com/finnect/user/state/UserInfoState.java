package com.finnect.user.state;

import com.finnect.user.vo.WorkspaceId;

public interface UserInfoState {

    String getEmail();

    String getFirstName();

    String getLastName();

    WorkspaceId getDefaultWorkspaceId();
}

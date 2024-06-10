package com.finnect.user.domain.state;

import com.finnect.common.vo.UserId;
import com.finnect.common.vo.WorkspaceId;

public interface UserState {

    UserId getId();

    String getUsername();

    String getPassword();

    String getEmail();

    String getFirstName();

    String getLastName();

    WorkspaceId getDefaultWorkspaceId();
}

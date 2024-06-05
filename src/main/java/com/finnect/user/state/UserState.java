package com.finnect.user.state;

import com.finnect.user.vo.UserId;
import com.finnect.user.vo.WorkspaceId;

public interface UserState {

    UserId getId();

    String getUsername();

    String getPassword();

    String getEmail();

    String getFirstName();

    String getLastName();

    WorkspaceId getDefaultWorkspaceId();
}

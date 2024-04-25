package com.finnect.user;

public interface UserInfoState {

    UserId getId();

    String getEmail();

    String getFirstName();

    String getLastName();

    WorkspaceId getDefaultWorkspaceId();
}

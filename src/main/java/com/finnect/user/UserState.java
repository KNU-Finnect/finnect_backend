package com.finnect.user;

public interface UserState {

    UserId getId();

    String getUsername();

    String getPassword();

    String getEmail();

    String getFirstName();

    String getLastName();

    WorkspaceId getAuthorizedWorkspaceId();
}

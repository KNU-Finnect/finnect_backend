package com.finnect.user.domain;

import com.finnect.user.vo.UserId;
import com.finnect.user.state.UserState;
import com.finnect.user.vo.WorkspaceId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
public class User implements UserState {

    @Getter
    private final UserId id;

    @Getter
    private final String username;

    @Getter
    private final String password;

    @NonNull
    private final UserInfo userInfo;


    @Override
    public String getEmail() {
        return userInfo.getEmail();
    }

    @Override
    public String getFirstName() {
        return userInfo.getFirstName();
    }

    @Override
    public String getLastName() {
        return userInfo.getLastName();
    }

    @Override
    public WorkspaceId getDefaultWorkspaceId() {
        return userInfo.getDefaultWorkspaceId();
    }


    public static User from(UserState user) {
        UserInfo userInfo = UserInfo.from(user);

        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .userInfo(userInfo)
                .build();
    }
}

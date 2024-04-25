package com.finnect.user.domain;

import com.finnect.user.UserId;
import com.finnect.user.UserInfoState;
import com.finnect.user.WorkspaceId;
import com.finnect.user.application.port.in.command.CreateUserCommand;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserInfo implements UserInfoState {

    private final UserId id;

    private final String email;

    private final String firstName;

    private final String lastName;

    private final WorkspaceId defaultWorkspaceId;

    public static UserInfo from(UserInfoState user) {
        return UserInfo.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .defaultWorkspaceId(user.getDefaultWorkspaceId())
                .build();
    }

    public static UserInfo from(CreateUserCommand createUser) {
        return UserInfo.builder()
                .email(createUser.getEmail())
                .firstName(createUser.getFirstName())
                .lastName(createUser.getLastName())
                .build();
    }
}

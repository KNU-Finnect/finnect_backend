package com.finnect.user.domain;

import com.finnect.user.state.UserInfoState;
import com.finnect.user.vo.WorkspaceId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class UserInfo implements UserInfoState {

    @NonNull
    private final String email;

    @NonNull
    private final String firstName;

    @NonNull
    private final String lastName;

    private WorkspaceId defaultWorkspaceId;

    public UserInfo(
            @NonNull String email,
            @NonNull String firstName,
            @NonNull String lastName,
            WorkspaceId defaultWorkspaceId
    ) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.defaultWorkspaceId = defaultWorkspaceId;
    }

    public static UserInfo from(UserInfoState user) {
        return UserInfo.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .defaultWorkspaceId(user.getDefaultWorkspaceId())
                .build();
    }
}

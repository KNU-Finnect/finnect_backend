package com.finnect.user.domain;

import com.finnect.user.state.UserInfoState;
import com.finnect.user.vo.UserId;
import com.finnect.user.vo.WorkspaceId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder @RequiredArgsConstructor
@Getter
public class UserInfo implements UserInfoState {

    private final UserId id;

    @NonNull
    private final String email;

    @NonNull
    private final String firstName;

    @NonNull
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
}

package com.finnect.user.domain;

import com.finnect.user.UserId;
import com.finnect.user.UserInfoState;
import com.finnect.user.WorkspaceId;
import com.finnect.user.application.port.in.command.CreateUserCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
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

    public UserInfo(
            UserId id,
            @NonNull String email,
            @NonNull String firstName,
            @NonNull String lastName,
            WorkspaceId defaultWorkspaceId
    ) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.defaultWorkspaceId = defaultWorkspaceId;
    }

    public static UserInfo from(UserInfoState user) {
        return UserInfo.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .defaultWorkspaceId(user.getDefaultWorkspaceId())
                .build();
    }

    public static UserInfo from(CreateUserCommand command) {
        return UserInfo.builder()
                .email(command.getEmail())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .build();
    }
}

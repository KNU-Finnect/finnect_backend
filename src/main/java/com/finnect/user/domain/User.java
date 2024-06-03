package com.finnect.user.domain;

import com.finnect.user.state.UserState;
import com.finnect.user.vo.UserId;
import com.finnect.user.vo.WorkspaceId;
import lombok.*;

@Builder @AllArgsConstructor
@Getter
public class User implements UserState {

    private final UserId id;

    @NonNull
    private final String username;

    @NonNull
    private String password;

    @NonNull
    private final String email;

    @NonNull
    private final String firstName;

    @NonNull
    private final String lastName;

    private WorkspaceId defaultWorkspaceId;


    public void changePassword(String password) {
        this.password = password;
    }

    public void changeDefaultWorkspace(WorkspaceId workspaceId) {
        this.defaultWorkspaceId = workspaceId;
    }
  
    public boolean hasDefaultWorkspace() {
        return defaultWorkspaceId != null;
    }


    public static User from(UserState user) {
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .defaultWorkspaceId(user.getDefaultWorkspaceId())
                .build();
    }
}

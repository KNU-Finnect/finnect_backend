package com.finnect.user.adapter.out.persistence.entity;

import com.finnect.common.vo.UserId;
import com.finnect.user.state.UserState;
import com.finnect.common.vo.WorkspaceId;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder @AllArgsConstructor()
@Entity @Table(name = "user")
public class UserEntity implements UserState {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Getter
    @Column(name = "username", unique = true)
    private String username;

    @Getter
    @Column(name = "password")
    private String password;

    @Getter @Setter
    @Column(name = "email")
    private String email;

    @Getter @Setter
    @Column(name = "first_name")
    private String firstName;

    @Getter @Setter
    @Column(name = "last_name")
    private String lastName;

    @Setter
    @Column(name = "default_workspace_id")
    private Long defaultWorkspaceId;

    @Override
    public UserId getId() {
        if (id != null) {
            return new UserId(id);
        } else {
            return null;
        }
    }

    @Override
    public WorkspaceId getDefaultWorkspaceId() {
        if (defaultWorkspaceId != null) {
            return new WorkspaceId(defaultWorkspaceId);
        } else {
            return null;
        }
    }

    public static UserEntity from(UserState user) {
        WorkspaceId workspaceId = user.getDefaultWorkspaceId();

        return UserEntity.builder()
                .id(user.getId().value())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .defaultWorkspaceId(workspaceId != null ? workspaceId.value() : null)
                .build();
    }
}

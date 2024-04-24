package com.finnect.auth.adapter.out.persistence;

import com.finnect.auth.UserId;
import com.finnect.auth.UserState;
import com.finnect.auth.WorkspaceId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor()
@Builder
public class UserJpaEntity implements UserState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Getter
    @Column(name = "username", unique = true)
    private String username;

    @Getter
    @Column(name = "password")
    private String password;

    @Getter
    @Column(name = "email")
    private String email;

    @Getter
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "default_workspace_id")
    private Long defaultWorkspaceId;

    @Override
    public UserId getId() {
        return new UserId(id);
    }

    @Override
    public WorkspaceId getAuthorizedWorkspaceId() {
        return new WorkspaceId(defaultWorkspaceId);
    }
}

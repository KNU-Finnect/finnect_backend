package com.finnect.user.adapter.out.persistence.entity;

import com.finnect.user.UserId;
import com.finnect.user.UserState;
import com.finnect.user.WorkspaceAuthority;
import com.finnect.user.WorkspaceId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor()
@Builder
public class UserEntity implements UserState {

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
    public WorkspaceId getDefaultWorkspaceId() {
        return new WorkspaceId(defaultWorkspaceId);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new WorkspaceAuthority(getDefaultWorkspaceId()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

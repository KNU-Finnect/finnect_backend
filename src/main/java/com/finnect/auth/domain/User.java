package com.finnect.auth.domain;

import com.finnect.auth.UserId;
import com.finnect.auth.UserState;
import com.finnect.auth.WorkspaceId;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Getter
public class User implements UserState, UserDetails {

    private final UserId id;

    private final String username;

    private final String password;

    private final String email;

    private final String firstName;

    private final String lastName;

    private final WorkspaceAuthority workspaceAuthority;

    @Override
    public WorkspaceId getAuthorizedWorkspaceId() {
        return workspaceAuthority.workspaceId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(workspaceAuthority);
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

    public static User from(UserState user) {
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .workspaceAuthority(new WorkspaceAuthority(user.getAuthorizedWorkspaceId()))
                .build();
    }
}

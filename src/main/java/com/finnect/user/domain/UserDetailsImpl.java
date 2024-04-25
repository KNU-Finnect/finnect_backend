package com.finnect.user.domain;

import com.finnect.user.WorkspaceAuthority;
import com.finnect.user.application.port.in.command.CreateUserCommand;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Builder
public class UserDetailsImpl implements UserDetails {

    private final String username;

    private final String password;

    private final WorkspaceAuthority workspaceAuthority;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(workspaceAuthority);
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

    public static UserDetailsImpl from(UserDetails user) {
        return UserDetailsImpl.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .workspaceAuthority(
                        WorkspaceAuthority.from(user.getAuthorities())
                )
                .build();
    }

    public static UserDetailsImpl from(CreateUserCommand createUser) {
        return UserDetailsImpl.builder()
                .username(createUser.getUsername())
                .password(createUser.getPassword())
                .build();
    }
}

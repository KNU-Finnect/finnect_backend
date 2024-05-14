package com.finnect.user.domain;

import com.finnect.user.vo.UserId;
import com.finnect.user.state.UserState;
import com.finnect.user.vo.WorkspaceAuthority;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Builder @RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    @Getter
    @NonNull
    private final UserId id;

    @Getter
    @NonNull
    private final String username;

    @Getter
    @NonNull
    private final String password;

    private final WorkspaceAuthority workspaceAuthority;


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


    public static UserDetailsImpl from(UserState user) {
        return UserDetailsImpl.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .workspaceAuthority(new WorkspaceAuthority(user.getDefaultWorkspaceId()))
                .build();
    }
}

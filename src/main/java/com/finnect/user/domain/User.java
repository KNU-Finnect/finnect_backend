package com.finnect.user.domain;

import com.finnect.user.UserId;
import com.finnect.user.UserState;
import com.finnect.user.WorkspaceId;
import com.finnect.user.application.port.in.CreateUserCommand;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Builder
public class User implements UserState {

    public final UserInfo userInfo;

    public final UserDetailsImpl userDetails;

    @Override
    public UserId getId() {
        return userInfo.getId();
    }

    @Override
    public String getEmail() {
        return userInfo.getEmail();
    }

    @Override
    public String getFirstName() {
        return userInfo.getFirstName();
    }

    @Override
    public String getLastName() {
        return userInfo.getLastName();
    }

    @Override
    public WorkspaceId getDefaultWorkspaceId() {
        return userInfo.getDefaultWorkspaceId();
    }

    @Override
    public String getUsername() {
        return userDetails.getUsername();
    }

    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userDetails.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDetails.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userDetails.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userDetails.isEnabled();
    }

    public static User from(UserState user) {
        return User.builder()
                .userInfo(
                        UserInfo.from(user)
                )
                .userDetails(
                        UserDetailsImpl.from(user)
                )
                .build();
    }

    public static User from(CreateUserCommand createUser) {
        return User.builder()
                .userInfo(
                        UserInfo.from(createUser)
                )
                .userDetails(
                        UserDetailsImpl.from(createUser)
                )
                .build();
    }
}

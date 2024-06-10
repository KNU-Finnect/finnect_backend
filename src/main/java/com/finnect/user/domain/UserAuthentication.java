package com.finnect.user.domain;

import com.finnect.user.domain.state.UserAuthenticationState;
import com.finnect.common.vo.UserId;
import com.finnect.common.vo.WorkspaceAuthority;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Builder @RequiredArgsConstructor
public class UserAuthentication implements UserAuthenticationState {

    @NonNull
    private final UserId userId;

    @NonNull
    @Getter
    private final String username;

    private final WorkspaceAuthority workspaceAuthority;


    @Override
    public String getUserId() {
        return String.valueOf(userId);
    }

    @Override
    public Collection<String> getAuthorities() {
        return Collections.singleton(workspaceAuthority.getAuthority());
    }


    public static UserAuthentication from(Authentication authentication) {
        return UserAuthentication.builder()
                .userId(Objects.requireNonNull(UserId.parseOrNull(authentication.getDetails())))
                .username(authentication.getName())
                .workspaceAuthority(WorkspaceAuthority.from(authentication.getAuthorities()))
                .build();
    }
}

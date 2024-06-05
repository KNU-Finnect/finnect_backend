package com.finnect.user.adapter.out.jwt.entity;

import com.finnect.user.state.UserAuthenticationState;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Builder @RequiredArgsConstructor
public class JwtAuthentication implements UserAuthenticationState {

    @Getter
    private final String userId;

    @Getter
    private final String username;

    @Getter
    private final Collection<String> authorities;

    public static JwtAuthentication from(UserAuthenticationState authentication) {
        return JwtAuthentication.builder()
                .userId(authentication.getUserId())
                .username(authentication.getUsername())
                .authorities(authentication.getAuthorities())
                .build();
    }
}

package com.finnect.user.adapter.in.security.util;

import com.finnect.user.domain.state.UserAuthenticationState;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;

public final class AuthenticationUtils {

    public static Authentication from(UserAuthenticationState userAuthentication) {
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (String authority: userAuthentication.getAuthorities()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userAuthentication.getUsername(),
                "",
                grantedAuthorities
        );
        authenticationToken.setDetails(userAuthentication.getUserId());
        return authenticationToken;
    }
}

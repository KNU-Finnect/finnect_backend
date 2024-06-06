package com.finnect.user.application.port.in;

import com.finnect.user.state.RefreshTokenState;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsQuery extends UserDetailsService {

    UserDetails loadUserByRefreshToken(RefreshTokenState refreshToken);
}

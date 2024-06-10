package com.finnect.user.application;

import com.finnect.user.application.port.in.UserDetailsQuery;
import com.finnect.user.application.port.out.LoadUserPort;
import com.finnect.user.application.port.out.error.UserNotFoundException;
import com.finnect.user.domain.User;
import com.finnect.user.domain.UserDetailsImpl;
import com.finnect.user.domain.state.RefreshTokenState;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsQuery {

    private final LoadUserPort loadUserPort;

    @Override
    public UserDetails loadUserByRefreshToken(RefreshTokenState refreshToken) throws UserNotFoundException {
        User user = User.from(loadUserPort.loadUser(refreshToken.getUserId()));

        return UserDetailsImpl.of(user, refreshToken.getWorkspaceId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        User user = User.from(loadUserPort.loadUserByUsername(username));

        return UserDetailsImpl.from(user);
    }
}

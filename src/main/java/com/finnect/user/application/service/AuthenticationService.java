package com.finnect.user.application.service;

import com.finnect.user.application.port.in.ReissueUseCase;
import com.finnect.user.application.port.in.command.ReissueCommand;
import com.finnect.user.application.port.out.LoadUserPort;
import com.finnect.user.application.jwt.JwtProvider;
import com.finnect.user.domain.User;
import com.finnect.user.domain.UserDetailsImpl;
import com.finnect.user.exception.InvalidRefreshTokenException;
import com.finnect.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService, ReissueUseCase {

    private final LoadUserPort loadUserPort;

    private final JwtProvider tokenProvider;

    @Autowired
    public AuthenticationService(
            LoadUserPort loadUserPort,
            JwtProvider tokenProvider
    ) {
        this.loadUserPort = loadUserPort;

        this.tokenProvider = tokenProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;

        try {
            user = User.from(loadUserPort.loadUser(username));
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }

        return UserDetailsImpl.from(user);
    }

    @Override
    public String reissue(ReissueCommand command) {
        Authentication authentication;

        try {
            authentication = tokenProvider.obtainAuthentication(command.getRefreshToken());
        } catch (Exception e) {
            throw new InvalidRefreshTokenException(e.getMessage(), e);
        }

        return tokenProvider.generateAccessToken(authentication).value();
    }
}

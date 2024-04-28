package com.finnect.user.application.service;

import com.finnect.user.application.port.in.ReissueUseCase;
import com.finnect.user.application.port.in.command.CreateAccessTokenCommand;
import com.finnect.user.application.port.out.GetUserDetailsPort;
import com.finnect.user.application.security.jwt.AccessToken;
import com.finnect.user.application.security.jwt.JwtProvider;
import com.finnect.user.exception.InvalidRefreshTokenException;
import com.finnect.user.exception.UserNotFoundException;
import com.finnect.user.domain.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService, ReissueUseCase {

    public final GetUserDetailsPort getUserDetailsPort;

    public final PasswordEncoder passwordEncoder;

    private final JwtProvider tokenProvider;

    @Autowired
    public AuthenticationService(
            GetUserDetailsPort getUserDetailsPort,
            PasswordEncoder passwordEncoder,
            JwtProvider tokenProvider
    ) {
        this.getUserDetailsPort = getUserDetailsPort;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user;

        try {
            user = getUserDetailsPort.getUserDetails(username);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }

        return UserDetailsImpl.from(user);
    }

    @Override
    public AccessToken reissue(CreateAccessTokenCommand command) {
        Authentication authentication;

        try {
            authentication = tokenProvider.obtainAuthentication(command.getRefreshToken());
        } catch (Exception e) {
            throw new InvalidRefreshTokenException(e.getMessage(), e);
        }

        return tokenProvider.generateAccessToken(authentication);
    }
}

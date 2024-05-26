package com.finnect.user.application.service;

import com.finnect.user.application.port.in.AuthenticateUseCase;
import com.finnect.user.application.port.in.UserDetailsQuery;
import com.finnect.user.application.port.in.command.AuthenticateCommand;
import com.finnect.user.domain.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements AuthenticateUseCase {

    private final UserDetailsQuery userDetailsQuery;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(
            UserDetailsQuery userDetailsQuery,
            PasswordEncoder passwordEncoder
    ) {
        this.userDetailsQuery = userDetailsQuery;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(AuthenticateCommand command) {
        // Find user
        UserDetailsImpl userFound = (UserDetailsImpl) userDetailsQuery.loadUserByUsername(command.getUsername());

        // Verify password
        if (!passwordEncoder.matches(command.getPassword(), userFound.getPassword())) {
            throw new BadCredentialsException(userFound.getUsername());
        }

        // Complete login
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userFound.getUsername(),
                "",
                userFound.getAuthorities()
        );
        authenticationToken.setDetails(userFound.getId());
        return authenticationToken;
    }
}

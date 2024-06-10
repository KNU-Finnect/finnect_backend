package com.finnect.user.application.service;

import com.finnect.user.application.port.in.AuthenticateUseCase;
import com.finnect.user.application.port.in.UserDetailsQuery;
import com.finnect.user.application.port.in.command.AuthenticateCommand;
import com.finnect.user.domain.UserAuthentication;
import com.finnect.user.domain.UserDetailsImpl;
import com.finnect.user.state.UserAuthenticationState;
import com.finnect.common.vo.WorkspaceAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService implements AuthenticateUseCase {

    private final UserDetailsQuery userDetailsQuery;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticateService(
            UserDetailsQuery userDetailsQuery,
            PasswordEncoder passwordEncoder
    ) {
        this.userDetailsQuery = userDetailsQuery;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAuthenticationState authenticate(AuthenticateCommand command) {
        UserDetailsImpl user = (UserDetailsImpl) userDetailsQuery.loadUserByUsername(command.getUsername());

        if (!passwordEncoder.matches(command.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(user.getUsername());
        }

        return UserAuthentication.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .workspaceAuthority(WorkspaceAuthority.from(user.getAuthorities()))
                .build();
    }
}

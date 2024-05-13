package com.finnect.user.adapter.in.security;

import com.finnect.user.application.port.in.AuthenticateUseCase;
import com.finnect.user.application.port.in.command.AuthenticateCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private final AuthenticateUseCase authenticateUseCase;

    @Autowired
    public AuthenticationProviderImpl(
            AuthenticateUseCase authenticateUseCase
    ) {
        this.authenticateUseCase = authenticateUseCase;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticateCommand command = AuthenticateCommand.builder()
                .username(authentication.getName())
                .password(authentication.getCredentials().toString())
                .build();

        return authenticateUseCase.authenticate(command);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

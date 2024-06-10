package com.finnect.user.adapter.in.security;

import com.finnect.user.adapter.in.security.util.AuthenticationUtils;
import com.finnect.user.application.port.in.AuthenticateUseCase;
import com.finnect.user.application.port.in.command.AuthenticateCommand;
import com.finnect.user.domain.state.UserAuthenticationState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private final AuthenticateUseCase authenticateUseCase;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticateCommand command = AuthenticateCommand.builder()
                .username(authentication.getName())
                .password(authentication.getCredentials().toString())
                .build();

        UserAuthenticationState userAuthentication = authenticateUseCase.authenticate(command);

        return AuthenticationUtils.from(userAuthentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

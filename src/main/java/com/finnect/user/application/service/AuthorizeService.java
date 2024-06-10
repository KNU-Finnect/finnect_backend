package com.finnect.user.application.service;

import com.finnect.user.application.port.in.AuthorizeUseCase;
import com.finnect.user.application.port.in.command.AuthorizeCommand;
import com.finnect.user.application.port.out.ObtainAuthenticationPort;
import com.finnect.user.domain.AccessToken;
import com.finnect.user.domain.state.UserAuthenticationState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService implements AuthorizeUseCase {

    private final ObtainAuthenticationPort obtainAuthenticationPort;

    @Autowired
    public AuthorizeService(
            ObtainAuthenticationPort obtainAuthenticationPort
    ) {
        this.obtainAuthenticationPort = obtainAuthenticationPort;
    }

    @Override
    public UserAuthenticationState authorize(AuthorizeCommand command) {
        AccessToken accessToken = new AccessToken(command.getToken());

        return obtainAuthenticationPort.obtainAuthentication(accessToken.toString());
    }
}

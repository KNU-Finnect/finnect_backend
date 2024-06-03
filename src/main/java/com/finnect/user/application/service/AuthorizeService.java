package com.finnect.user.application.service;

import com.finnect.user.adapter.out.jwt.JwtProvider;
import com.finnect.user.application.port.in.AuthorizeUseCase;
import com.finnect.user.application.port.in.command.AuthorizeCommand;
import com.finnect.user.domain.AccessToken;
import com.finnect.user.state.UserAuthenticationState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService implements AuthorizeUseCase {

    private final JwtProvider tokenProvider;

    @Autowired
    public AuthorizeService(
            JwtProvider tokenProvider
    ) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public UserAuthenticationState authorize(AuthorizeCommand command) {
        AccessToken accessToken = new AccessToken(command.getToken());

        return tokenProvider.obtainAuthentication(accessToken.toString());
    }
}

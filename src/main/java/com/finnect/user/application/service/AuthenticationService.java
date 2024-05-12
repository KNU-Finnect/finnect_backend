package com.finnect.user.application.service;

import com.finnect.user.application.jwt.JwtProvider;
import com.finnect.user.application.port.in.AuthorizeUseCase;
import com.finnect.user.application.port.in.IssueUseCase;
import com.finnect.user.application.port.in.ReissueUseCase;
import com.finnect.user.application.port.in.UserDetailsQuery;
import com.finnect.user.application.port.in.command.AuthorizeCommand;
import com.finnect.user.application.port.in.command.IssueCommand;
import com.finnect.user.application.port.in.command.ReissueCommand;
import com.finnect.user.application.port.out.LoadRefreshTokenPort;
import com.finnect.user.application.port.out.LoadUserPort;
import com.finnect.user.application.port.out.SaveRefreshTokenPort;
import com.finnect.user.domain.*;
import com.finnect.user.exception.UserNotFoundException;
import com.finnect.user.state.AccessTokenState;
import com.finnect.user.state.TokenPairState;
import com.finnect.user.vo.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticationService implements UserDetailsQuery, IssueUseCase, ReissueUseCase, AuthorizeUseCase {

    private final LoadUserPort loadUserPort;
    private final LoadRefreshTokenPort loadRefreshTokenPort;
    private final SaveRefreshTokenPort saveRefreshTokenPort;

    private final JwtProvider tokenProvider;

    @Autowired
    public AuthenticationService(
            LoadUserPort loadUserPort,
            LoadRefreshTokenPort loadRefreshTokenPort,
            SaveRefreshTokenPort saveRefreshTokenPort,
            JwtProvider tokenProvider
    ) {
        this.loadUserPort = loadUserPort;
        this.loadRefreshTokenPort = loadRefreshTokenPort;
        this.saveRefreshTokenPort = saveRefreshTokenPort;

        this.tokenProvider = tokenProvider;
    }

    @Override
    public UserDetails loadUserById(UserId userId) throws UserNotFoundException {
        User user = User.from(loadUserPort.loadUser(userId));

        return UserDetailsImpl.from(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        User user = User.from(loadUserPort.loadUser(username));

        return UserDetailsImpl.from(user);
    }

    @Override
    public TokenPairState issue(IssueCommand command) {
        Authentication authentication = command.getAuthentication();

        AccessToken accessToken = new AccessToken(tokenProvider.generateAccessToken(authentication));
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .userId(UserId.parseOrNull(authentication.getDetails().toString()))
                .build();

        saveRefreshTokenPort.saveToken(refreshToken);

        return TokenPair.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AccessTokenState reissue(ReissueCommand command) {
        RefreshToken refreshToken = RefreshToken.from(loadRefreshTokenPort.loadToken(command.getRefreshToken()));
        UserDetails user = loadUserById(refreshToken.getUserId());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                "",
                user.getAuthorities()
        );

        return new AccessToken(tokenProvider.generateAccessToken(authenticationToken));
    }

    @Override
    public void authorize(AuthorizeCommand command) {
        AccessToken accessToken = new AccessToken(command.getToken());

        if (tokenProvider.validateToken(accessToken.toString())) {
            Authentication authentication = tokenProvider.obtainAuthentication(accessToken.toString());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            SecurityContextHolder.clearContext();
        }
    }
}

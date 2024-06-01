package com.finnect.user.application.service;

import com.finnect.user.application.jwt.JwtProvider;
import com.finnect.user.application.port.in.*;
import com.finnect.user.application.port.in.command.AuthorizeCommand;
import com.finnect.user.application.port.in.command.IssueCommand;
import com.finnect.user.application.port.in.command.ReissueCommand;
import com.finnect.user.application.port.out.LoadRefreshTokenPort;
import com.finnect.user.application.port.out.SaveRefreshTokenPort;
import com.finnect.user.domain.*;
import com.finnect.user.state.AccessTokenState;
import com.finnect.user.state.TokenPairState;
import com.finnect.user.vo.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService implements IssueUseCase, ReissueUseCase, AuthorizeUseCase {

    private final UserDetailsQuery userDetailsQuery;

    private final LoadRefreshTokenPort loadRefreshTokenPort;
    private final SaveRefreshTokenPort saveRefreshTokenPort;

    private final JwtProvider tokenProvider;
    private final Long refreshExpirationSecond;


    @Autowired
    public TokenService(
            UserDetailsQuery userDetailsQuery,
            LoadRefreshTokenPort loadRefreshTokenPort,
            SaveRefreshTokenPort saveRefreshTokenPort,
            JwtProvider tokenProvider,
            @Value("${backend.refresh-expiration-second}") Long refreshExpirationSecond
    ) {
        this.userDetailsQuery = userDetailsQuery;

        this.loadRefreshTokenPort = loadRefreshTokenPort;
        this.saveRefreshTokenPort = saveRefreshTokenPort;

        this.tokenProvider = tokenProvider;
        this.refreshExpirationSecond = refreshExpirationSecond;
    }

    @Override
    public TokenPairState issue(IssueCommand command) {
        Authentication authentication = command.getAuthentication();

        AccessToken accessToken = new AccessToken(tokenProvider.generateAccessToken(authentication));
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .userId(UserId.parseOrNull(authentication.getDetails().toString()))
                .expirationSecond(refreshExpirationSecond)
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
        UserDetails user = userDetailsQuery.loadUserById(refreshToken.getUserId());

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

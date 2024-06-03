package com.finnect.user.application.service;

import com.finnect.user.adapter.out.jwt.JwtProvider;
import com.finnect.user.application.port.in.AuthorizeUseCase;
import com.finnect.user.application.port.in.IssueUseCase;
import com.finnect.user.application.port.in.ReissueUseCase;
import com.finnect.user.application.port.in.UserDetailsQuery;
import com.finnect.user.application.port.in.command.AuthorizeCommand;
import com.finnect.user.application.port.in.command.IssueCommand;
import com.finnect.user.application.port.in.command.ReissueCommand;
import com.finnect.user.application.port.in.command.ReissueWorkspaceCommand;
import com.finnect.user.application.port.out.LoadRefreshTokenPort;
import com.finnect.user.application.port.out.SaveRefreshTokenPort;
import com.finnect.user.domain.*;
import com.finnect.user.state.AccessTokenState;
import com.finnect.user.state.TokenPairState;
import com.finnect.user.state.UserAuthenticationState;
import com.finnect.user.vo.UserId;
import com.finnect.user.vo.WorkspaceAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        UserAuthentication authentication = command.getAuthentication();

        AccessToken accessToken = new AccessToken(tokenProvider.generateAccessToken(authentication));
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .userId(UserId.parseOrNull(authentication.getUserId()))
                .workspaceId(WorkspaceAuthority.of(authentication.getAuthorities()).workspaceId())
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

        UserDetailsImpl user = (UserDetailsImpl) userDetailsQuery.loadUserByRefreshToken(refreshToken);

        UserAuthentication authentication = UserAuthentication.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .workspaceAuthority(WorkspaceAuthority.from(user.getAuthorities()))
                .build();

        return new AccessToken(tokenProvider.generateAccessToken(authentication));
    }

    @Override
    public AccessTokenState reissueWorkspace(ReissueWorkspaceCommand command) {
        RefreshToken refreshToken = RefreshToken.from(loadRefreshTokenPort.loadToken(command.getRefreshToken()));
        refreshToken.moveWorkspace(command.getWorkspaceId());

        UserDetailsImpl user = (UserDetailsImpl) userDetailsQuery.loadUserByRefreshToken(refreshToken);

        UserAuthentication authentication = UserAuthentication.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .workspaceAuthority(WorkspaceAuthority.from(user.getAuthorities()))
                .build();

        saveRefreshTokenPort.saveToken(refreshToken);

        return new AccessToken(tokenProvider.generateAccessToken(authentication));
    }

    @Override
    public UserAuthenticationState authorize(AuthorizeCommand command) {
        AccessToken accessToken = new AccessToken(command.getToken());

        return tokenProvider.obtainAuthentication(accessToken.toString());
    }
}

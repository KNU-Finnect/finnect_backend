package com.finnect.user.application.service;

import com.finnect.user.application.port.in.IssueUseCase;
import com.finnect.user.application.port.in.ReissueUseCase;
import com.finnect.user.application.port.in.UserDetailsQuery;
import com.finnect.user.application.port.in.command.IssueCommand;
import com.finnect.user.application.port.in.command.ReissueCommand;
import com.finnect.user.application.port.in.command.ReissueWorkspaceCommand;
import com.finnect.user.application.port.in.error.ReissueWorkspaceException;
import com.finnect.user.application.port.out.GenerateAccessTokenPort;
import com.finnect.user.application.port.out.LoadRefreshTokenPort;
import com.finnect.user.application.port.out.SaveRefreshTokenPort;
import com.finnect.user.domain.*;
import com.finnect.user.state.AccessTokenState;
import com.finnect.user.state.TokenPairState;
import com.finnect.common.vo.UserId;
import com.finnect.common.vo.WorkspaceAuthority;
import com.finnect.workspace.application.port.in.CheckWorkspaceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IssueService implements IssueUseCase, ReissueUseCase {

    private final UserDetailsQuery userDetailsQuery;
    private final CheckWorkspaceQuery checkWorkspaceQuery;

    private final LoadRefreshTokenPort loadRefreshTokenPort;
    private final SaveRefreshTokenPort saveRefreshTokenPort;

    private final GenerateAccessTokenPort generateAccessTokenPort;
    private final Long refreshExpirationSecond;


    @Autowired
    public IssueService(
            UserDetailsQuery userDetailsQuery,
            CheckWorkspaceQuery checkWorkspaceQuery,
            LoadRefreshTokenPort loadRefreshTokenPort,
            SaveRefreshTokenPort saveRefreshTokenPort,
            GenerateAccessTokenPort generateAccessTokenPort,
            @Value("${backend.refresh-expiration-second}") Long refreshExpirationSecond
    ) {
        this.userDetailsQuery = userDetailsQuery;
        this.checkWorkspaceQuery = checkWorkspaceQuery;

        this.loadRefreshTokenPort = loadRefreshTokenPort;
        this.saveRefreshTokenPort = saveRefreshTokenPort;

        this.generateAccessTokenPort = generateAccessTokenPort;
        this.refreshExpirationSecond = refreshExpirationSecond;
    }

    @Override
    public TokenPairState issue(IssueCommand command) {
        UserAuthentication authentication = command.getAuthentication();

        AccessToken accessToken = new AccessToken(generateAccessTokenPort.generateAccessToken(authentication));
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

        return new AccessToken(generateAccessTokenPort.generateAccessToken(authentication));
    }

    @Override
    public AccessTokenState reissueWorkspace(ReissueWorkspaceCommand command) {
        RefreshToken refreshToken = RefreshToken.from(loadRefreshTokenPort.loadToken(command.getRefreshToken()));
        refreshToken.moveWorkspace(command.getWorkspaceId());

        UserDetailsImpl user = (UserDetailsImpl) userDetailsQuery.loadUserByRefreshToken(refreshToken);

        if (!checkWorkspaceQuery.checkWorkspace(user.getId(), command.getWorkspaceId())) {
            throw new ReissueWorkspaceException(user.getId(), command.getWorkspaceId());
        }

        UserAuthentication authentication = UserAuthentication.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .workspaceAuthority(WorkspaceAuthority.from(user.getAuthorities()))
                .build();

        saveRefreshTokenPort.saveToken(refreshToken);

        return new AccessToken(generateAccessTokenPort.generateAccessToken(authentication));
    }
}

package com.finnect.user.domain;

import com.finnect.user.state.RefreshTokenState;
import com.finnect.common.vo.UserId;
import com.finnect.common.vo.WorkspaceId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder @AllArgsConstructor
public class RefreshToken implements RefreshTokenState {

    private final String token;

    @Getter
    private final UserId userId;

    @Getter
    private WorkspaceId workspaceId;

    @Getter
    private final Long expirationSecond;


    public void moveWorkspace(WorkspaceId workspaceId) {
        this.workspaceId = workspaceId;
    }


    @Override
    public String toString() {
        return token;
    }

    public static RefreshToken from(RefreshTokenState refreshToken) {
        return RefreshToken.builder()
                .token(refreshToken.toString())
                .userId(refreshToken.getUserId())
                .workspaceId(refreshToken.getWorkspaceId())
                .expirationSecond(refreshToken.getExpirationSecond())
                .build();
    }
}

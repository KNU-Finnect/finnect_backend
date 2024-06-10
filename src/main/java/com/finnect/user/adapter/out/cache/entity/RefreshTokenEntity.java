package com.finnect.user.adapter.out.cache.entity;

import com.finnect.user.state.RefreshTokenState;
import com.finnect.common.vo.UserId;
import com.finnect.common.vo.WorkspaceId;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder @AllArgsConstructor
@RedisHash(value = "refresh-token")
public class RefreshTokenEntity implements RefreshTokenState {

    @Id
    private String token;

    private Long userId;

    private Long workspaceId;

    @TimeToLive
    @Getter
    private Long expirationSecond;

    public UserId getUserId() {
        return new UserId(userId);
    }

    public WorkspaceId getWorkspaceId() {
        return new WorkspaceId(workspaceId);
    }


    @Override
    public String toString() {
        return token;
    }

    public static RefreshTokenEntity from(RefreshTokenState refreshToken) {
        WorkspaceId workspaceId = refreshToken.getWorkspaceId();

        return RefreshTokenEntity.builder()
                .token(refreshToken.toString())
                .userId(refreshToken.getUserId().value())
                .workspaceId(workspaceId != null ? workspaceId.value() : null)
                .expirationSecond(refreshToken.getExpirationSecond())
                .build();
    }
}

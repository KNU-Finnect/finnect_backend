package com.finnect.user.adapter.out.cache.entity;

import com.finnect.user.state.RefreshTokenState;
import com.finnect.user.vo.UserId;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder @AllArgsConstructor
@Getter
@RedisHash(value = "refreshToken", timeToLive = 14440)
public class RefreshTokenEntity implements RefreshTokenState {

    @Id
    private String token;

    private UserId userId;
    @TimeToLive
    @Getter
    private Long expirationSecond;

    public static RefreshTokenEntity from(RefreshTokenState refreshToken) {
        return RefreshTokenEntity.builder()
                .userId(refreshToken.getUserId())
                .token(refreshToken.toString())
                .expirationSecond(refreshToken.getExpirationSecond())
                .build();
    }
}

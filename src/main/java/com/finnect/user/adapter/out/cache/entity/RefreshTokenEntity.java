package com.finnect.user.adapter.out.cache.entity;

import com.finnect.user.state.RefreshTokenState;
import com.finnect.user.vo.UserId;
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

    @TimeToLive
    @Getter
    private Long expirationSecond;

    public UserId getUserId() {
        return new UserId(userId);
    }

    public static RefreshTokenEntity from(RefreshTokenState refreshToken) {
        return RefreshTokenEntity.builder()
                .token(refreshToken.toString())
                .userId(refreshToken.getUserId().value())
                .expirationSecond(refreshToken.getExpirationSecond())
                .build();
    }
}

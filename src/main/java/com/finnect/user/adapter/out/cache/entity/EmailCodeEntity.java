package com.finnect.user.adapter.out.cache.entity;

import com.finnect.user.domain.state.EmailCodeState;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder @AllArgsConstructor
@RedisHash(value = "auth-code")
public class EmailCodeEntity implements EmailCodeState {

    @Id
    @Getter
    private String email;

    @Getter
    private Integer number;

    @TimeToLive
    @Getter
    private Long expirationSecond;

    private Boolean verified;

    @Override
    public Boolean isVerified() {
        return verified;
    }

    public static EmailCodeEntity from(EmailCodeState emailCode) {
        return EmailCodeEntity.builder()
                .email(emailCode.getEmail())
                .number(emailCode.getNumber())
                .expirationSecond(emailCode.getExpirationSecond())
                .verified(emailCode.isVerified())
                .build();
    }
}

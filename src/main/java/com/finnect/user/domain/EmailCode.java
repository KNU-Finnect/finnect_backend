package com.finnect.user.domain;

import com.finnect.user.domain.state.EmailCodeState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder @AllArgsConstructor
public class EmailCode implements EmailCodeState {

    @Getter
    private final String email;

    @Getter
    private final Integer number;

    @Getter
    private final Long expirationSecond;

    private Boolean verified;

    @Override
    public Boolean isVerified() {
        return verified;
    }

    public void verify(Integer number) {
        verified = this.number.equals(number);
    }

    public static EmailCode from(EmailCodeState emailCode) {
        return EmailCode.builder()
                .email(emailCode.getEmail())
                .number(emailCode.getNumber())
                .expirationSecond(emailCode.getExpirationSecond())
                .verified(emailCode.isVerified())
                .build();
    }
}

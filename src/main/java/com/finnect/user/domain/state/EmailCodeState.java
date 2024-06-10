package com.finnect.user.domain.state;

public interface EmailCodeState {

    String getEmail();

    Integer getNumber();

    Long getExpirationSecond();

    Boolean isVerified();
}

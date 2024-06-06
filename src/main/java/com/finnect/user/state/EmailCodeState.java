package com.finnect.user.state;

public interface EmailCodeState {

    String getEmail();

    Integer getNumber();

    Long getExpirationSecond();

    Boolean isVerified();
}

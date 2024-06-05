package com.finnect.user.state;

import java.util.Collection;

public interface UserAuthenticationState {

    String getUserId();

    String getUsername();

    Collection<String> getAuthorities();
}

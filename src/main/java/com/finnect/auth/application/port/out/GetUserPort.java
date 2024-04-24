package com.finnect.auth.application.port.out;

import com.finnect.auth.UserState;

public interface GetUserPort {

    UserState getUser(String username);
}

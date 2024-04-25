package com.finnect.user.application.port.out;

import com.finnect.user.UserState;

public interface GetUserPort {

    UserState getUser(String username) throws UserNotFoundException;
}

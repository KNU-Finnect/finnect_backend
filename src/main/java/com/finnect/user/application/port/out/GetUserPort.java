package com.finnect.user.application.port.out;

import com.finnect.user.vo.UserId;
import com.finnect.user.state.UserState;
import com.finnect.user.exception.UserNotFoundException;

public interface GetUserPort {

    UserState getUser(UserId userId) throws UserNotFoundException;

    UserState getUser(String username) throws UserNotFoundException;
}

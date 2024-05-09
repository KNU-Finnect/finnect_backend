package com.finnect.user.application.port.out;

import com.finnect.user.vo.UserId;
import com.finnect.user.state.UserState;
import com.finnect.user.exception.UserNotFoundException;

public interface LoadUserPort {

    UserState loadUser(UserId userId) throws UserNotFoundException;

    UserState loadUser(String username) throws UserNotFoundException;
}

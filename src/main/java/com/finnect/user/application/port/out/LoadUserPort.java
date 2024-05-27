package com.finnect.user.application.port.out;

import com.finnect.user.application.port.out.exception.UserNotFoundException;
import com.finnect.user.state.UserState;
import com.finnect.user.vo.UserId;

public interface LoadUserPort {

    UserState loadUser(UserId userId) throws UserNotFoundException;

    UserState loadUserByUsername(String username) throws UserNotFoundException;

    UserState loadUserByEmail(String email) throws UserNotFoundException;
}

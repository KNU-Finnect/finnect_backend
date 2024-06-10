package com.finnect.user.application.port.out;

import com.finnect.user.application.port.out.error.UserNotFoundException;
import com.finnect.user.domain.state.UserState;
import com.finnect.common.vo.UserId;

public interface LoadUserPort {

    UserState loadUser(UserId userId) throws UserNotFoundException;

    UserState loadUserByUsername(String username) throws UserNotFoundException;

    UserState loadUserByEmail(String email) throws UserNotFoundException;
}

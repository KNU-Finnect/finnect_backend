package com.finnect.user.application.port.out;

import com.finnect.user.domain.state.UserState;

public interface CreateUserPort {

    void createUser(UserState user);
}

package com.finnect.user.application.port.out;

import com.finnect.user.state.UserState;

public interface CreateUserPort {

    void createUser(UserState user);
}

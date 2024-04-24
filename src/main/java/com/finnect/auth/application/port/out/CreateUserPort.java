package com.finnect.auth.application.port.out;

import com.finnect.auth.UserState;

public interface CreateUserPort {

    void createUser(UserState user);
}

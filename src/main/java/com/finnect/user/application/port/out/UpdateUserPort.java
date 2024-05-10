package com.finnect.user.application.port.out;

import com.finnect.user.state.UserState;

public interface UpdateUserPort {

    void updateUser(UserState user);
}

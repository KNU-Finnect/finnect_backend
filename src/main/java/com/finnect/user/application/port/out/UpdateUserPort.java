package com.finnect.user.application.port.out;

import com.finnect.user.domain.state.UserState;

public interface UpdateUserPort {

    void updateUser(UserState user);
}

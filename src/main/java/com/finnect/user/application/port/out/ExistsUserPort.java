package com.finnect.user.application.port.out;

import com.finnect.common.vo.UserId;

public interface ExistsUserPort {
    boolean existsUserById(UserId userId);

    boolean existsUserByUsername(String Username);

    boolean existsUserByEmail(String email);
}

package com.finnect.user.application.port.in;

import com.finnect.common.vo.UserId;

public interface CheckUserQuery {

    boolean checkUserExists(UserId userId);
}

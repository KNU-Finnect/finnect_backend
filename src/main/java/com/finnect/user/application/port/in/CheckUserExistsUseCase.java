package com.finnect.user.application.port.in;

import com.finnect.common.vo.UserId;

public interface CheckUserExistsUseCase {

    boolean checkUserExists(UserId userId);
}

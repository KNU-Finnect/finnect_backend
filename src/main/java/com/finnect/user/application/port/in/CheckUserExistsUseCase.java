package com.finnect.user.application.port.in;

import com.finnect.user.vo.UserId;

public interface CheckUserExistsUseCase {

    boolean checkUserExists(UserId userId);
}

package com.finnect.user.application.port.in;

import com.finnect.user.vo.UserId;

public interface GetNameUseCase {

    String getNameById(UserId userId);
}

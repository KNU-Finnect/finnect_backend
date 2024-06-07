package com.finnect.user.application.port.in;

import com.finnect.user.application.port.out.error.UserNotFoundException;
import com.finnect.user.vo.UserId;

public interface GetPersonalNameQuery {

    String getPersonalName(UserId userId) throws UserNotFoundException;
}

package com.finnect.user.application.port.in;

import com.finnect.user.application.port.out.error.UserNotFoundException;
import com.finnect.common.vo.UserId;

public interface CheckDefaultWorkspaceQuery {

    boolean checkDefaultWorkspace(UserId userId) throws UserNotFoundException;
}

package com.finnect.user.application.port.in;

import com.finnect.user.vo.UserId;

public interface CheckDefaultWorkspaceUseCase {

    boolean checkDefaultWorkspace(UserId userId);
}

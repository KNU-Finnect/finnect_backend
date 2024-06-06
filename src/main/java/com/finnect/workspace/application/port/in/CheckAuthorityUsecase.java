package com.finnect.workspace.application.port.in;

public interface CheckAuthorityUsecase {

    boolean checkAuthorityOfUser(Long userId, Long workspaceId);
}

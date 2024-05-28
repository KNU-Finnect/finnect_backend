package com.finnect.user.application.port.in;

import org.springframework.stereotype.Component;

@Component
public class MockService implements CheckDefaultWorkspaceUsecase, SetDefaultUsecase {

    @Override
    public boolean checkDefaultWorkspace(Long userId) {
        if (userId > 5L)
            return false;
        return true;
    }

    @Override
    public boolean setDefault(Long workspaceId) {
        return true;
    }
}

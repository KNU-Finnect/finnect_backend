package com.finnect.user.application.port.in;

import org.springframework.stereotype.Component;

@Component
public class MockService implements SetDefaultUsecase {

    @Override
    public boolean setDefault(Long workspaceId) {
        return true;
    }
}

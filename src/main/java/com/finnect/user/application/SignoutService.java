package com.finnect.user.application;

import com.finnect.user.application.port.in.SignoutUseCase;
import com.finnect.user.application.port.in.command.SignoutCommand;
import com.finnect.user.application.port.out.DeleteRefreshTokenPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignoutService implements SignoutUseCase {

    private final DeleteRefreshTokenPort deleteRefreshTokenPort;


    @Autowired
    public SignoutService(
            DeleteRefreshTokenPort deleteRefreshTokenPort
    ) {
        this.deleteRefreshTokenPort = deleteRefreshTokenPort;
    }

    @Override
    public void signout(SignoutCommand command) {
        deleteRefreshTokenPort.deleteRefreshToken(command.getRefreshToken());
    }
}

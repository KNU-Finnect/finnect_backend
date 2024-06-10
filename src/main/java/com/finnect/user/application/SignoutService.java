package com.finnect.user.application;

import com.finnect.user.application.port.in.SignoutUseCase;
import com.finnect.user.application.port.in.command.SignoutCommand;
import com.finnect.user.application.port.out.DeleteRefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignoutService implements SignoutUseCase {

    private final DeleteRefreshTokenPort deleteRefreshTokenPort;

    @Override
    public void signout(SignoutCommand command) {
        deleteRefreshTokenPort.deleteRefreshToken(command.getRefreshToken());
    }
}

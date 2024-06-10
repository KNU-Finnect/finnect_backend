package com.finnect.user.application;

import com.finnect.user.application.port.in.ChangePasswordUseCase;
import com.finnect.user.application.port.in.command.ChangePasswordCommand;
import com.finnect.user.application.port.out.LoadUserPort;
import com.finnect.user.application.port.out.UpdateUserPort;
import com.finnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordService implements ChangePasswordUseCase {

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void changePassword(ChangePasswordCommand command) {
        User user = User.from(loadUserPort.loadUser(command.getUserId()));
        user.changePassword(passwordEncoder.encode(command.getPassword()));

        updateUserPort.updateUser(user);
    }
}

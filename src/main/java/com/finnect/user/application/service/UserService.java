package com.finnect.user.application.service;

import com.finnect.user.application.port.in.command.CreateUserCommand;
import com.finnect.user.application.port.in.SignupUseCase;
import com.finnect.user.application.port.out.CreateUserPort;
import com.finnect.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements SignupUseCase {

    public final CreateUserPort createUserPort;

    @Autowired
    public UserService(CreateUserPort createUserPort) {
        this.createUserPort = createUserPort;
    }

    @Override
    public Object signup(CreateUserCommand createUser) {
        createUserPort.createUser(
                User.from(createUser)
        );
        return null;
    }
}

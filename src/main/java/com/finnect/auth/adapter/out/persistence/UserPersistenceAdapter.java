package com.finnect.auth.adapter.out.persistence;

import com.finnect.auth.UserState;
import com.finnect.auth.application.port.out.GetUserPort;
import com.finnect.auth.application.port.out.CreateUserPort;
import com.finnect.auth.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceAdapter implements GetUserPort, CreateUserPort {

    private final UserRepository userRepository;

    @Autowired
    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserState getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public void createUser(UserState user) {
        userRepository.save(UserJpaEntity.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build());
    }
}

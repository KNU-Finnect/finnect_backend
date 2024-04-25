package com.finnect.user.adapter.out.persistence;

import com.finnect.user.UserState;
import com.finnect.user.adapter.out.persistence.entity.UserEntity;
import com.finnect.user.adapter.out.persistence.entity.UserRepository;
import com.finnect.user.application.port.out.CreateUserPort;
import com.finnect.user.application.port.out.GetUserDetailsPort;
import com.finnect.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceAdapter implements GetUserDetailsPort, CreateUserPort {

    private final UserRepository userRepository;

    @Autowired
    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails getUserDetails(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public void createUser(UserState user) {
        userRepository.save(UserEntity.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build());
    }
}

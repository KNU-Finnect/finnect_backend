package com.finnect.user.adapter.out.persistence;

import com.finnect.user.application.port.out.ExistsUserPort;
import com.finnect.user.application.port.out.UpdateUserPort;
import com.finnect.user.vo.UserId;
import com.finnect.user.state.UserState;
import com.finnect.user.adapter.out.persistence.entity.UserEntity;
import com.finnect.user.adapter.out.persistence.entity.UserRepository;
import com.finnect.user.application.port.out.CreateUserPort;
import com.finnect.user.application.port.out.LoadUserPort;
import com.finnect.user.application.port.out.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceAdapter implements CreateUserPort, ExistsUserPort, LoadUserPort, UpdateUserPort {

    private final UserRepository userRepository;

    @Autowired
    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void createUser(UserState userState) {
        UserEntity userEntity = UserEntity.builder()
                .username(userState.getUsername())
                .password(userState.getPassword())
                .email(userState.getEmail())
                .firstName(userState.getFirstName())
                .lastName(userState.getLastName())
                .build();

        userRepository.save(userEntity);
    }


    @Override
    public boolean existsUserById(UserId userId) {
        return userRepository.existsById(userId.value());
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }


    @Override
    public UserState loadUser(UserId userId) throws UserNotFoundException {
        return userRepository.findById(userId.value())
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public UserState loadUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("username", username));
    }

    @Override
    public UserState loadUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email));
    }


    @Override
    public void updateUser(UserState userState) {
        UserEntity userEntity = UserEntity.from(userState);

        userRepository.save(userEntity);
    }
}

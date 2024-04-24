package com.finnect.auth.application;

import com.finnect.auth.UserState;
import com.finnect.auth.application.port.out.GetUserPort;
import com.finnect.auth.application.port.out.UserNotFoundException;
import com.finnect.auth.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    public final GetUserPort getUserPort;

    public final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(
            GetUserPort getUserPort,
            PasswordEncoder passwordEncoder
    ) {
        this.getUserPort = getUserPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserState user;

        try {
            user = getUserPort.getUser(username);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }

        return User.from(user);
    }
}

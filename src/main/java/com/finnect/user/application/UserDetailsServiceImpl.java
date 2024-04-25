package com.finnect.user.application;

import com.finnect.user.application.port.out.GetUserDetailsPort;
import com.finnect.user.application.port.out.UserNotFoundException;
import com.finnect.user.domain.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public final GetUserDetailsPort getUserDetailsPort;

    public final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(
            GetUserDetailsPort getUserDetailsPort,
            PasswordEncoder passwordEncoder
    ) {
        this.getUserDetailsPort = getUserDetailsPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user;

        try {
            user = getUserDetailsPort.getUserDetails(username);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }

        return UserDetailsImpl.from(user);
    }
}

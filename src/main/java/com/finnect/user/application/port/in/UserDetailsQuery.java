package com.finnect.user.application.port.in;

import com.finnect.user.exception.UserNotFoundException;
import com.finnect.user.vo.UserId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsQuery extends UserDetailsService {

    UserDetails loadUserById(UserId userId) throws UserNotFoundException;
}

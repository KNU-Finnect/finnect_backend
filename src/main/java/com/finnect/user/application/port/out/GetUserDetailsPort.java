package com.finnect.user.application.port.out;

import com.finnect.user.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface GetUserDetailsPort {

    UserDetails getUserDetails(String username) throws UserNotFoundException;
}

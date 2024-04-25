package com.finnect.user.application.port.out;

import org.springframework.security.core.userdetails.UserDetails;

public interface GetUserDetailsPort {

    UserDetails getUserDetails(String username);
}

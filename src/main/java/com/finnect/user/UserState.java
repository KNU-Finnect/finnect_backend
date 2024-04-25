package com.finnect.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserState extends UserInfoState, UserDetails {
}

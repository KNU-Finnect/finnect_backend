package com.finnect.user.state;

import com.finnect.user.vo.UserId;

public interface UserState extends UserInfoState {

    UserId getId();

    String getUsername();

    String getPassword();
}

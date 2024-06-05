package com.finnect.user.application.port.in.error;

import com.finnect.common.error.DuplicateException;

public class UserDuplicateException extends DuplicateException {

    public UserDuplicateException(String username) {
        super("User(%s) is duplicate".formatted(username));
    }
}

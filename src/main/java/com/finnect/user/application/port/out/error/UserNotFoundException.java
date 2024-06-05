package com.finnect.user.application.port.out.error;

import com.finnect.common.error.NotFoundException;
import com.finnect.user.vo.UserId;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(UserId userId) {
        super("User(userId = %s) is not found".formatted(userId));
    }

    public UserNotFoundException(String fieldName, String value) {
        super("User(%s = %s) is not found".formatted(fieldName, value));
    }
}

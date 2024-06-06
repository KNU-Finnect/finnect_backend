package com.finnect.user.application.port.out.error;

import com.finnect.common.error.NotFoundException;

public class EmailCodeNotFoundException extends NotFoundException {

    public EmailCodeNotFoundException(String email) {
        super("EmailCode(%s) is not found".formatted(email));
    }
}

package com.finnect.user.application.port.in.error;

import com.finnect.common.error.NotVerifiedException;

public class EmailCodeNotVerifiedException extends NotVerifiedException {

    public EmailCodeNotVerifiedException(String email) {
        super("EmailCode(%s) is not verified".formatted(email));
    }
}

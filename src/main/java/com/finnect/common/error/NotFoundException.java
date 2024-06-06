package com.finnect.common.error;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    static int statusCode = 404;

    public NotFoundException(String message) {
        super(message);
    }
}

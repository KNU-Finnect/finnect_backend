package com.finnect.user.application.port.in.command;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResetPasswordCommandTests {

    @Test
    void email_should_not_be_incorrect() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                ResetPasswordCommand.builder()
                        .email("user@google")
                        .build()
        );
    }
}

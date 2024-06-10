package com.finnect.user.application.port.in.command;

import com.finnect.common.vo.UserId;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChangePasswordCommandTests {

    private final UserId GIVEN_USER_ID = new UserId(1L);

    @Test
    void password_length_should_not_be_less_than_10() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                ChangePasswordCommand.builder()
                        .userId(GIVEN_USER_ID)
                        .password("Pass-1")
                        .build()
        );
    }

    @Test
    void password_length_should_not_be_greater_than_15() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                ChangePasswordCommand.builder()
                        .userId(GIVEN_USER_ID)
                        .password("Pass-1_Pass-2_Pass-3")
                        .build()
        );
    }

    @Test
    void password_should_contain_lowercase() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                ChangePasswordCommand.builder()
                        .userId(GIVEN_USER_ID)
                        .password("PASS-1_PASS-2")
                        .build()
        );
    }

    @Test
    void password_should_contain_uppercase() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                ChangePasswordCommand.builder()
                        .userId(GIVEN_USER_ID)
                        .password("pass-1_pass-2")
                        .build()
        );
    }

    @Test
    void password_should_contain_special_char() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                ChangePasswordCommand.builder()
                        .userId(GIVEN_USER_ID)
                        .password("Pass1Pass2")
                        .build()
        );
    }

    @Test
    void password_should_not_contain_korean() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                ChangePasswordCommand.builder()
                        .userId(GIVEN_USER_ID)
                        .password("Pass-1_패스-2")
                        .build()
        );
    }
}

package com.finnect.user.application.port.in.command;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SignupCommandTests {

    private final String CORRECT_USERNAME = "user@gmail.com";
    private final String CORRECT_PASSWORD = "Password-1";
    private final String CORRECT_EMAIL = "user@gmail.com";
    private final String CORRECT_FIRST_NAME = "Kim";
    private final String CORRECT_LAST_NAME = "Cheolsu";

    @Test
    void password_length_should_not_be_less_than_10() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME)
                        .password("Pass-1")
                        .email(CORRECT_EMAIL)
                        .firstName(CORRECT_FIRST_NAME)
                        .lastName(CORRECT_LAST_NAME)
                        .build()
        );
    }

    @Test
    void password_length_should_not_be_greater_than_15() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME)
                        .password("Pass-1_Pass-2_Pass-3")
                        .email(CORRECT_EMAIL)
                        .firstName(CORRECT_FIRST_NAME)
                        .lastName(CORRECT_LAST_NAME)
                        .build()
        );
    }

    @Test
    void password_should_contain_lowercase() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME)
                        .password("PASS-1_PASS-2")
                        .email(CORRECT_EMAIL)
                        .firstName(CORRECT_FIRST_NAME)
                        .lastName(CORRECT_LAST_NAME)
                        .build()
        );
    }

    @Test
    void password_should_contain_uppercase() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME)
                        .password("pass-1_pass-2")
                        .email(CORRECT_EMAIL)
                        .firstName(CORRECT_FIRST_NAME)
                        .lastName(CORRECT_LAST_NAME)
                        .build()
        );
    }

    @Test
    void password_should_contain_special_char() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME)
                        .password("Pass1Pass2")
                        .email(CORRECT_EMAIL)
                        .firstName(CORRECT_FIRST_NAME)
                        .lastName(CORRECT_LAST_NAME)
                        .build()
        );
    }

    @Test
    void password_should_not_contain_korean() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME)
                        .password("Pass-1_패스-2")
                        .email(CORRECT_EMAIL)
                        .firstName(CORRECT_FIRST_NAME)
                        .lastName(CORRECT_LAST_NAME)
                        .build()
        );
    }

    @Test
    void email_should_not_be_incorrect() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME)
                        .password(CORRECT_PASSWORD)
                        .email("user@google")
                        .firstName(CORRECT_FIRST_NAME)
                        .lastName(CORRECT_LAST_NAME)
                        .build()
        );
    }

    @Test
    void first_name_should_not_contain_special_char() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME)
                        .password(CORRECT_PASSWORD)
                        .email(CORRECT_EMAIL)
                        .firstName("Kim-")
                        .lastName(CORRECT_LAST_NAME)
                        .build()
        );
    }

    @Test
    void last_name_should_not_contain_special_char() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME)
                        .password(CORRECT_PASSWORD)
                        .email(CORRECT_EMAIL)
                        .firstName(CORRECT_FIRST_NAME)
                        .lastName("Cheolsu-")
                        .build()
        );
    }
}

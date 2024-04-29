package com.finnect.user.application.port.in.command;

import com.finnect.user.application.port.in.command.SignupCommand;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SignupCommandTests {

    final String CORRECT_USERNAME_EXAMPLE = "user-1";
    final String CORRECT_PASSWORD_EXAMPLE = "Password-1";
    final String CORRECT_EMAIL_EXAMPLE = "user@google.com";
    final String CORRECT_FIRST_NAME_EXAMPLE = "Kim";
    final String CORRECT_LAST_NAME_EXAMPLE = "Cheolsu";

    @Test
    void username_length_should_not_be_less_than_5() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username("us-1")
                        .password(CORRECT_PASSWORD_EXAMPLE)
                        .email(CORRECT_EMAIL_EXAMPLE)
                        .firstName(CORRECT_FIRST_NAME_EXAMPLE)
                        .lastName(CORRECT_LAST_NAME_EXAMPLE)
                        .build()
        );
    }

    @Test
    void username_length_should_not_be_greater_than_20() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username("user-1_user-2_user-3_user-4")
                        .password(CORRECT_PASSWORD_EXAMPLE)
                        .email(CORRECT_EMAIL_EXAMPLE)
                        .firstName(CORRECT_FIRST_NAME_EXAMPLE)
                        .lastName(CORRECT_LAST_NAME_EXAMPLE)
                        .build()
        );
    }

    @Test
    void username_should_not_contain_korean() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username("user-유저-1")
                        .password(CORRECT_PASSWORD_EXAMPLE)
                        .email(CORRECT_EMAIL_EXAMPLE)
                        .firstName(CORRECT_FIRST_NAME_EXAMPLE)
                        .lastName(CORRECT_LAST_NAME_EXAMPLE)
                        .build()
        );
    }

    @Test
    void password_length_should_not_be_less_than_10() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME_EXAMPLE)
                        .password("Pass-1")
                        .email(CORRECT_EMAIL_EXAMPLE)
                        .firstName(CORRECT_FIRST_NAME_EXAMPLE)
                        .lastName(CORRECT_LAST_NAME_EXAMPLE)
                        .build()
        );
    }

    @Test
    void password_length_should_not_be_greater_than_15() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME_EXAMPLE)
                        .password("Pass-1_Pass-2_Pass-3")
                        .email(CORRECT_EMAIL_EXAMPLE)
                        .firstName(CORRECT_FIRST_NAME_EXAMPLE)
                        .lastName(CORRECT_LAST_NAME_EXAMPLE)
                        .build()
        );
    }

    @Test
    void password_should_contain_lowercase() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME_EXAMPLE)
                        .password("PASS-1_PASS-2")
                        .email(CORRECT_EMAIL_EXAMPLE)
                        .firstName(CORRECT_FIRST_NAME_EXAMPLE)
                        .lastName(CORRECT_LAST_NAME_EXAMPLE)
                        .build()
        );
    }

    @Test
    void password_should_contain_uppercase() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME_EXAMPLE)
                        .password("pass-1_pass-2")
                        .email(CORRECT_EMAIL_EXAMPLE)
                        .firstName(CORRECT_FIRST_NAME_EXAMPLE)
                        .lastName(CORRECT_LAST_NAME_EXAMPLE)
                        .build()
        );
    }

    @Test
    void password_should_contain_special_char() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME_EXAMPLE)
                        .password("Pass1Pass2")
                        .email(CORRECT_EMAIL_EXAMPLE)
                        .firstName(CORRECT_FIRST_NAME_EXAMPLE)
                        .lastName(CORRECT_LAST_NAME_EXAMPLE)
                        .build()
        );
    }

    @Test
    void password_should_not_contain_korean() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME_EXAMPLE)
                        .password("Pass-1_패스-2")
                        .email(CORRECT_EMAIL_EXAMPLE)
                        .firstName(CORRECT_FIRST_NAME_EXAMPLE)
                        .lastName(CORRECT_LAST_NAME_EXAMPLE)
                        .build()
        );
    }

    @Test
    void first_name_should_not_contain_special_char() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME_EXAMPLE)
                        .password(CORRECT_PASSWORD_EXAMPLE)
                        .email(CORRECT_EMAIL_EXAMPLE)
                        .firstName("Kim-")
                        .lastName(CORRECT_LAST_NAME_EXAMPLE)
                        .build()
        );
    }

    @Test
    void last_name_should_not_contain_special_char() {
        Assertions.assertThrows(ConstraintViolationException.class, () ->
                SignupCommand.builder()
                        .username(CORRECT_USERNAME_EXAMPLE)
                        .password(CORRECT_PASSWORD_EXAMPLE)
                        .email(CORRECT_EMAIL_EXAMPLE)
                        .firstName(CORRECT_FIRST_NAME_EXAMPLE)
                        .lastName("Cheolsu-")
                        .build()
        );
    }
}

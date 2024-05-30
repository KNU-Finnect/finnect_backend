package com.finnect.user.application.password;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordGeneratorTests {

    @Test
    void password_length_should_not_be_less_than_10() {
        // given
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        // when
        String password = passwordGenerator.generateRandomPassword();

        // then
        Assertions.assertTrue(password.length() >= 10);
    }

    @Test
    void password_length_should_not_be_greater_than_15() {
        // given
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        // when
        String password = passwordGenerator.generateRandomPassword();

        // then
        Assertions.assertTrue(password.length() <= 15);
    }

    @Test
    void password_should_contain_lowercase() {
        // given
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        // when
        String password = passwordGenerator.generateRandomPassword();

        // then
        Assertions.assertTrue(password.matches(".*[a-z].*"));
    }

    @Test
    void password_should_contain_uppercase() {
        // given
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        // when
        String password = passwordGenerator.generateRandomPassword();

        // then
        Assertions.assertTrue(password.matches(".*[A-Z].*"));
    }

    @Test
    void password_should_contain_special_char() {
        // given
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        // when
        String password = passwordGenerator.generateRandomPassword();

        // then
        Assertions.assertTrue(password.matches(".*[-_].*"));
    }

    @Test
    void password_should_not_contain_korean() {
        // given
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        // when
        String password = passwordGenerator.generateRandomPassword();

        // then
        Assertions.assertFalse(password.matches(".*[가-힣].*"));
    }
}

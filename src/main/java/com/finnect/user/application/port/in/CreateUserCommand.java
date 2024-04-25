package com.finnect.user.application.port.in;

public interface CreateUserCommand {

    String getUsername();

    String getPassword();

    String getEmail();

    String getFirstName();

    String getLastName();
}

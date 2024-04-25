package com.finnect.user.application.port.in.command;

public interface CreateUserCommand {

    String getUsername();

    String getPassword();

    String getEmail();

    String getFirstName();

    String getLastName();
}

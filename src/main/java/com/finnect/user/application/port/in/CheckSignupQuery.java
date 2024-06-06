package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.CheckSignupsCommand;

import java.util.Map;

public interface CheckSignupQuery {

    Map<String, Boolean> checkSignups(CheckSignupsCommand command);
}

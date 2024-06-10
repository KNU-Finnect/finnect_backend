package com.finnect.user.application.port.in;

import java.util.Collection;
import java.util.Map;

public interface CheckEmailQuery {

    Map<String, Boolean> checkEmailsExist(Collection<String> emails);
}

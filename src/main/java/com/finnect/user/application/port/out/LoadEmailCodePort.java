package com.finnect.user.application.port.out;

import com.finnect.user.application.port.out.error.EmailCodeNotFoundException;
import com.finnect.user.state.EmailCodeState;

public interface LoadEmailCodePort {

    EmailCodeState loadEmailCode(String email) throws EmailCodeNotFoundException;
}

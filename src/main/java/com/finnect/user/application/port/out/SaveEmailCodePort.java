package com.finnect.user.application.port.out;

import com.finnect.user.application.port.out.error.EmailCodeNotFoundException;
import com.finnect.user.state.EmailCodeState;

public interface SaveEmailCodePort {

    void saveEmailCode(EmailCodeState emailCode) throws EmailCodeNotFoundException;
}

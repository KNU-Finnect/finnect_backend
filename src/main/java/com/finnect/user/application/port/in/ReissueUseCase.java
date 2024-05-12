package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.ReissueCommand;
import com.finnect.user.state.AccessTokenState;

public interface ReissueUseCase {

    AccessTokenState reissue(ReissueCommand command);
}

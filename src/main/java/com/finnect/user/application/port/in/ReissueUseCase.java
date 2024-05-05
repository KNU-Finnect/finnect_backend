package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.ReissueCommand;

public interface ReissueUseCase {

    String reissue(ReissueCommand command);
}

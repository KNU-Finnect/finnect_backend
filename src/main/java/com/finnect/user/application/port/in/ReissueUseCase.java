package com.finnect.user.application.port.in;

import com.finnect.user.application.port.in.command.ReissueCommand;
import com.finnect.user.application.port.in.command.ReissueWorkspaceCommand;
import com.finnect.user.state.AccessTokenState;
import com.finnect.user.state.TokenPairState;

public interface ReissueUseCase {

    AccessTokenState reissue(ReissueCommand command);

    TokenPairState reissueWorkspace(ReissueWorkspaceCommand command);
}

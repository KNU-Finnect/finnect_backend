package com.finnect.workspace.application.port.out;

import com.finnect.workspace.domain.state.MemberState;

public interface GetMemberPort {

    MemberState getMember(Long userId, Long workspaceId);
}

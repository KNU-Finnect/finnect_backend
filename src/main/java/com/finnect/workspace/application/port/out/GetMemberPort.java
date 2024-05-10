package com.finnect.workspace.application.port.out;

import com.finnect.workspace.MemberState;

public interface GetMemberPort {

    MemberState getMember(Long userId, Long workspaceId);
}

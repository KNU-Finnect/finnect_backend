package com.finnect.workspace.application.port.out;

import com.finnect.workspace.domain.state.MemberState;

public interface SaveMemberPort {

    MemberState saveMember(MemberState memberState);
}

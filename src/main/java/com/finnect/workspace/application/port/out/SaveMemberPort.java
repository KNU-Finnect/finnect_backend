package com.finnect.workspace.application.port.out;

import com.finnect.workspace.MemberState;

public interface SaveMemberPort {

    MemberState saveMember(MemberState memberState);
}

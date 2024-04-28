package com.finnect.workspace.application.port.in;

import com.finnect.workspace.MemberState;

public interface CreateMemberUsecase {

    MemberState createMember(CreateMemberCommand cmd);
}

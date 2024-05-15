package com.finnect.workspace.application.port.in;

import com.finnect.workspace.domain.state.MemberState;

public interface CreateMemberUsecase {

    MemberState createMember(CreateMemberCommand cmd);
}

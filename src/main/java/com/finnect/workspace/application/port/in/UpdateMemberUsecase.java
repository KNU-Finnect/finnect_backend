package com.finnect.workspace.application.port.in;

import com.finnect.workspace.domain.state.MemberState;

public interface UpdateMemberUsecase {

    MemberState updateMember(UpdateMemberCommand command);
}

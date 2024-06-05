package com.finnect.workspace.application.port.in;

import com.finnect.workspace.domain.state.MemberState;

import java.util.List;

public interface FindMembersUsecase {

    List<MemberState> loadMembersByWorkspace(Long workspaceID);
}

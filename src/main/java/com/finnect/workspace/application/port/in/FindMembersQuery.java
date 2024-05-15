package com.finnect.workspace.application.port.in;

import com.finnect.workspace.domain.state.MemberState;

import java.util.List;

public interface FindMembersQuery {

    List<MemberState> loadMembersByWorkspace(Long workspaceID);
}

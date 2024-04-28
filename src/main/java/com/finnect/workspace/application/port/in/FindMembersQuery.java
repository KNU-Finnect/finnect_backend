package com.finnect.workspace.application.port.in;

import com.finnect.workspace.MemberState;

import java.util.List;

public interface FindMembersQuery {

    List<MemberState> loadMembersByWorkspace(Long workspaceID);
}

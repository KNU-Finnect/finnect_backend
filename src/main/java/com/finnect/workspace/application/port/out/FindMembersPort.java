package com.finnect.workspace.application.port.out;

import com.finnect.workspace.MemberState;

import java.util.List;

public interface FindMembersPort {

    List<MemberState> findMembersByWorkspace(Long workspaceId);
}

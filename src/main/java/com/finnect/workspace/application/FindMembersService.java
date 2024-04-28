package com.finnect.workspace.application;

import com.finnect.workspace.MemberState;
import com.finnect.workspace.application.port.in.FindMembersQuery;
import com.finnect.workspace.application.port.out.FindMembersPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindMembersService implements FindMembersQuery {

    private final FindMembersPort findMembersPort;

    @Override
    public List<MemberState> loadMembersByWorkspace(Long workspaceID) {
        return findMembersPort.findMembersByWorkspace(workspaceID);
    }
}

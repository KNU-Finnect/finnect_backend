package com.finnect.workspace.application;

import com.finnect.workspace.MemberState;
import com.finnect.workspace.application.port.in.ExitWorkspaceUsecase;
import com.finnect.workspace.application.port.out.DeleteWorkspacePort;
import com.finnect.workspace.application.port.out.ExitWorkspacePort;
import com.finnect.workspace.application.port.out.FindMembersPort;
import com.finnect.workspace.application.port.out.GetMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExitWorkspaceService implements ExitWorkspaceUsecase {
    private final GetMemberPort getMemberPort;
    private final ExitWorkspacePort exitWorkspacePort;
    private final FindMembersPort findMembersPort;
    private final DeleteWorkspacePort deleteWorkspacePort;

    @Override
    public boolean exit(Long userId, Long workspaceId) {
        MemberState member = getMemberPort.getMember(userId, workspaceId);

        exitWorkspacePort.exit(member);

        if (findMembersPort.findMembersByWorkspace(workspaceId).isEmpty()) {
            deleteWorkspacePort.delete(workspaceId);
        }

        return true;
    }
}

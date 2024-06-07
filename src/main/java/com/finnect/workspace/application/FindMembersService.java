package com.finnect.workspace.application;

import com.finnect.workspace.domain.state.MemberState;
import com.finnect.workspace.application.port.in.FindMembersUsecase;
import com.finnect.workspace.application.port.out.FindMembersPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class FindMembersService implements FindMembersUsecase {

    private final FindMembersPort findMembersPort;

    @Override
    public List<MemberState> loadMembersByWorkspace(Long workspaceID) {
        return findMembersPort.findMembersByWorkspace(workspaceID);
    }
}
